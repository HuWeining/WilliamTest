package com.example.demo.service.recommendation.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.algorithm.RecommendationSorting;
import com.example.demo.entity.travelResource.*;
import com.example.demo.model.CustomerVO;
import com.example.demo.model.TravelPlanVO;
import com.example.demo.model.TravelResourceItemVO;
import com.example.demo.model.TravelSiteVO;
import com.example.demo.repository.*;
import com.example.demo.service.StompService;
import com.example.demo.service.recommendation.RecommendationService;
import com.example.demo.service.travelResource.TravelResourceService;
import com.example.demo.utilsAndConsants.RecommendationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by huweining on 2017/7/23.
 */
@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    TravelPlanRepository travelPlanRepository;

    @Autowired
    TravelResourceItemRepository travelResourceItemRepository;

    @Autowired
    TravelSiteRepository travelSiteRepository;

    @Autowired
    TravelSiteBindingItemRepository bindingRepository;

    @Autowired
    TravelSiteBindingItemTempRepository bindingTempRepository;

    @Autowired
    TravelResourceService travelResourceService;

    @Autowired
    StompService stompService;

    @Override
    public List<TravelPlanVO> getDefaultRecommendationList() {
        List<TravelPlan> travelPlanList = (ArrayList)travelPlanRepository.findAll();
        List<TravelPlanVO> travelPlanVOList = travelPlanList.stream().map(travelPlan -> {
            //find travel plan for existed travel plan database
            return travelResourceService.transformTravelPlanToTravelPlanVO(travelPlan,true);
        }).collect(Collectors.toList());
        RecommendationSorting.travelPlanSort(travelPlanVOList);
        return travelPlanVOList;
    }

    @Override
    public TravelPlanVO buildTempTravelPlan(List<Integer> travelResourceItemIds, List<Integer> travelSiteIds) {
        List<TravelSite> travelSiteList = travelSiteRepository.findByIdIn(travelSiteIds);
        List<TravelResourceItem> travelResourceItemList = travelResourceItemRepository.findByIdIn(travelResourceItemIds);
        List<TravelResourceItemVO> travelResourceItemVOList = travelResourceItemList.stream().map(travelResourceItem-> {
            return travelResourceService.transformTravelResourceItemToTravelResourceItemVO(travelResourceItem);
        }).collect(Collectors.toList());
        List<TravelSiteVO> travelSiteVOList = travelSiteList.stream().map(travelSite-> {
            return travelResourceService.transformTravelSiteToTravelSiteVO(travelSite);
        }).collect(Collectors.toList());
        TravelPlanVO travelPlanVO = buildTempTravelPlanVO(travelSiteVOList,travelResourceItemVOList, travelResourceItemList);
        return travelPlanVO;
    }

    private TravelPlanVO buildTempTravelPlanVO(List<TravelSiteVO> route, List<TravelResourceItemVO> travelResourceItemVOList, List<TravelResourceItem> travelResourceItemList) {
        TravelPlan newTravelPlan = new TravelPlan();
        List<Integer> travelSiteIds = new ArrayList<>();
        List<Integer> bindingItemIds = new ArrayList<>();
        String travelPlanName = "";
        Integer area = null;
        Integer scene = null;
        Integer season = null;
        Integer suitAge = null;
        Integer category = null;
        for(TravelResourceItem travelResourceItem : travelResourceItemList){
            if(area == null){
                area = travelResourceItem.getArea();
            }else {
                area = area | travelResourceItem.getArea();
            }
            if(scene == null){
                scene = travelResourceItem.getScene();
            }else {
                scene = scene | travelResourceItem.getScene();
            }
            if(season == null){
                season = travelResourceItem.getSeason();
            }else {
                season = season & travelResourceItem.getSeason();
            }
            if(suitAge == null){
                suitAge = travelResourceItem.getSuitAge();
            }else {
                suitAge = suitAge & travelResourceItem.getSuitAge();
            }
            if(category == null){
                category = travelResourceItem.getCategory();
            }else {
                category = category | travelResourceItem.getCategory();
            }
        }
        for(TravelSiteVO travelSiteVO : route){
            travelPlanName = travelPlanName+travelSiteVO.getSiteName()+",";
            travelSiteIds.add(travelSiteVO.getId());
            TravelSiteBindingItemTemp travelSiteBindingItemTemp = new TravelSiteBindingItemTemp();
            for(TravelResourceItemVO travelResourceItemVO : travelResourceItemVOList){
                if(travelResourceItemVO.getTravelSiteId() == travelSiteVO.getId()){
                    if(travelSiteVO.getTravelResourceItemVOs() == null){
                        travelSiteVO.setTravelResourceItemVOs(new ArrayList<>());
                        travelSiteVO.setTravelResourceItemVOIds(new ArrayList<>());
                    }
                    travelSiteVO.getTravelResourceItemVOs().add(travelResourceItemVO);
                    travelSiteVO.getTravelResourceItemVOIds().add(travelResourceItemVO.getId());
                }
            }
            String travelResourceItemIdsJsonString = JSON.toJSONString(travelSiteVO.getTravelResourceItemVOIds());
            travelSiteBindingItemTemp.setTravelSiteId(travelSiteVO.getId());
            travelSiteBindingItemTemp.setTravelResourceItemIds(travelResourceItemIdsJsonString);
            bindingItemIds.add(bindingTempRepository.save(travelSiteBindingItemTemp).getId());
        }
        newTravelPlan.setAlreadyExisted(false);
        newTravelPlan.setArea(area);
        newTravelPlan.setScene(scene);
        newTravelPlan.setSeason(season);
        newTravelPlan.setSuitAge(suitAge);
        newTravelPlan.setCategory(category);
        newTravelPlan.setTravelSiteIds(JSON.toJSONString(travelSiteIds));
        newTravelPlan.setTravelSiteBindingItemIds(JSON.toJSONString(bindingItemIds));
        newTravelPlan.setName(travelPlanName.substring(0,travelPlanName.length()-1)+" travel plan");
        travelResourceService.calPriceAndCost(newTravelPlan);
        newTravelPlan = travelPlanRepository.save(newTravelPlan);
        TravelPlanVO travelPlanVO = travelResourceService.transformTravelPlanToTravelPlanVO(newTravelPlan,false);
        return travelPlanVO;
    }

    @Override
    public List<TravelPlanVO> findTravelPlanByTags(Integer area, Integer scene, Integer season, Integer suitAge, Integer category, boolean alreadyExisted) {
//        List<TravelPlan> travelPlanList = travelPlanRepository.findTravelPlanByTags(area,scene,season,suitAge,category,alreadyExisted);
        List<TravelPlan> allTravelPlanList = (ArrayList)travelPlanRepository.findByAlreadyExisted(alreadyExisted);
        Set<Integer> idSetAlreadyContains = new HashSet<>();
        List<TravelPlan> travelPlanList = filterTravelPlanListByTags(area,scene,season,suitAge,category,true,true,
                true,true,true,allTravelPlanList,idSetAlreadyContains);
        List<TravelPlanVO> travelPlanVOList = travelPlanList.stream().map(travelPlan -> {
            //find travel plan for existed travel plan database
            TravelPlanVO travelPlanVO = travelResourceService.transformTravelPlanToTravelPlanVO(travelPlan,alreadyExisted);
            travelPlanVO.setMatchDegree(1);
            return travelPlanVO;
        }).collect(Collectors.toList());

        /**
         * if match all the factor's List is enough,
         *     just do the grading and sort;
         * else
         *     get more travel plan with less match degree,
         *     then do the grading and sort.
         * return travelPlanVOList(size <= 10)
         */
        RecommendationSorting.travelPlanSort(travelPlanVOList);
        if(travelPlanVOList.size() >= RecommendationConstant.DEFAULT_RECOMMENDATION_SIZE){
            travelPlanVOList = travelPlanVOList.subList(0, RecommendationConstant.DEFAULT_RECOMMENDATION_SIZE);
        }else {
            List<TravelPlanVO> lessMatchTravelPlanVOList = getLessMatchTravelPlanList(area,scene,season,suitAge,category,alreadyExisted,allTravelPlanList,idSetAlreadyContains);
            RecommendationSorting.travelPlanSort(lessMatchTravelPlanVOList);
            if(lessMatchTravelPlanVOList.size() < RecommendationConstant.DEFAULT_RECOMMENDATION_SIZE - travelPlanVOList.size()){
                travelPlanVOList.addAll(lessMatchTravelPlanVOList);
            }else {
                travelPlanVOList.addAll(lessMatchTravelPlanVOList.subList(0,RecommendationConstant.DEFAULT_RECOMMENDATION_SIZE - travelPlanVOList.size()));
            }
        }
        return travelPlanVOList;
    }

    private List<TravelPlan> filterTravelPlanListByTags(Integer area, Integer scene, Integer season, Integer suitAge, Integer category,
                                                        Boolean filterArea, Boolean filterScene, Boolean filterSeason, Boolean filterSuitAge, Boolean filterCategory,
                                                        List<TravelPlan> allTravelPlanList, Set<Integer> idSetAlreadyContains){
        List<TravelPlan> travelPlanList = allTravelPlanList.stream().filter(travelPlan -> {
            if(idSetAlreadyContains.contains(travelPlan.getId()))
                return false;
            if(filterArea && area != 0 && !((area & travelPlan.getArea()) == area)){
                return false;
            }
            if(filterScene && scene != 0 && !((scene & travelPlan.getScene()) == scene)){
                return false;
            }
            if(filterSeason && season != 0 && !((season & travelPlan.getSeason()) == season)){
                return false;
            }
            if(filterSuitAge && suitAge != 0 && !((suitAge & travelPlan.getSuitAge()) == suitAge)){
                return false;
            }
            if(filterCategory && category != 0 && !((category & travelPlan.getCategory()) == category)){
                return false;
            }
            idSetAlreadyContains.add(travelPlan.getId());
            return true;
        }).collect(Collectors.toList());
        return travelPlanList;
    }

    @Override
    public List<TravelResourceItemVO> findTravelResourceItemByTravelSiteId(Integer travelSiteId) {
        TravelSiteBindingItem travelSiteBindingItem = bindingRepository.findByTravelSiteId(travelSiteId);
        List<Integer> travelResourceItemIds = JSON.parseArray(travelSiteBindingItem.getTravelResourceItemIds(), Integer.class);
        List<TravelResourceItemVO> travelResourceItemVOList = travelResourceItemIds.stream().map(travelResourceItemId -> {
            return travelResourceService.transformTravelResourceItemToTravelResourceItemVO(travelResourceItemRepository.findOne(travelResourceItemId));
        }).collect(Collectors.toList());
        return travelResourceItemVOList;
    }

    private List<TravelPlanVO> getLessMatchTravelPlanList(Integer area, Integer scene, Integer season, Integer suitAge, Integer category, Boolean alreadyExisted, List<TravelPlan> allTravelPlanList, Set<Integer> idSetAlreadyContains){
        List<TravelPlan> lessMatchTravelPlanList = new ArrayList<>();
        if(area != 0){
            lessMatchTravelPlanList.addAll(filterTravelPlanListByTags(area,scene,season,suitAge,category,false,true,true,true,true,allTravelPlanList,idSetAlreadyContains));
        }
        if(scene != 0){
            lessMatchTravelPlanList.addAll(filterTravelPlanListByTags(area,scene,season,suitAge,category,true,false,true,true,true,allTravelPlanList,idSetAlreadyContains));
        }
        if(season != 0){
            lessMatchTravelPlanList.addAll(filterTravelPlanListByTags(area,scene,season,suitAge,category,true,true,false,true,true,allTravelPlanList,idSetAlreadyContains));
        }
        if(suitAge != 0){
            lessMatchTravelPlanList.addAll(filterTravelPlanListByTags(area,scene,season,suitAge,category,true,true,true,false,true,allTravelPlanList,idSetAlreadyContains));
        }
        if(category != 0){
            lessMatchTravelPlanList.addAll(filterTravelPlanListByTags(area,scene,season,suitAge,category,true,true,true,true,false,allTravelPlanList,idSetAlreadyContains));
        }
        return lessMatchTravelPlanList.stream().map(travelPlan -> {
            //find travel plan for existed travel plan database
            TravelPlanVO travelPlanVO = travelResourceService.transformTravelPlanToTravelPlanVO(travelPlan,true);
            double count = getQueryCriteriaNumber(area, scene, season, suitAge, category);
            travelPlanVO.setMatchDegree((count-1)/count);
            return travelPlanVO;
        }).collect(Collectors.toList());
    }

    private double getQueryCriteriaNumber(Integer area, Integer scene, Integer season, Integer suitAge, Integer category){
        double count = 0;
        if(area != 0)
            count++;
        if(scene != 0)
            count++;
        if(season != 0)
            count++;
        if(suitAge != 0)
            count++;
        if(category != 0)
            count++;
        return count;
    }
}
