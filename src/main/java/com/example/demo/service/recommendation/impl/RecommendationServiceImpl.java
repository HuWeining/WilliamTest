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
import com.example.demo.utilsAndConsants.RecommendationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    StompService stompService;

    @Override
    public List<TravelPlanVO> getDefaultRecommendationList() {
        return null;
    }

    @Override
    public TravelPlanVO buildTempTravelPlan(List<TravelSiteVO> route, CustomerVO customer) {
        TravelPlan newTravelPlan = new TravelPlan();
        List<Integer> travelSiteIds = new ArrayList<>();
        List<Integer> bindingItemIds = new ArrayList<>();
        for(TravelSiteVO travelSiteVO : route){
            travelSiteIds.add(travelSiteVO.getId());

            TravelSiteBindingItemTemp travelSiteBindingItemTemp = new TravelSiteBindingItemTemp();
            List<Integer> travelResourceItemIds = new ArrayList<>();
            for(TravelResourceItemVO resourceItemVO : travelSiteVO.getResourceItemVOList()){
                travelResourceItemIds.add(resourceItemVO.getId());
            }
            String travelResourceItemIdsJsonString = JSON.toJSONString(travelSiteBindingItemTemp);
            travelSiteBindingItemTemp.setTravelSiteId(travelSiteVO.getId());
            travelSiteBindingItemTemp.setTravelResourceItemIds(travelResourceItemIdsJsonString);
            bindingItemIds.add(bindingTempRepository.save(travelSiteBindingItemTemp).getId());
        }
        newTravelPlan.setTravelSiteIds(JSON.toJSONString(travelSiteIds));
        newTravelPlan.setTravelSiteBindingItemIds(JSON.toJSONString(bindingItemIds));
        newTravelPlan = travelPlanRepository.save(newTravelPlan);

        TravelPlanVO travelPlanVO = transformTravelPlanToTravelPlanVO(newTravelPlan,false);
        return travelPlanVO;
    }

    @Override
    public List<TravelPlanVO> findTravelPlan(Integer area, Integer scene, Integer season, Integer artificial, Integer climate) {
        List<TravelPlan> travelPlanList = travelPlanRepository.findTravelPlanByTags(area,scene,season,artificial,climate);
        List<TravelPlanVO> travelPlanVOList = travelPlanList.stream().map(travelPlan -> {
            //find travel plan for existed travel plan database
            return transformTravelPlanToTravelPlanVO(travelPlan,true);
        }).collect(Collectors.toList());

        /**
         * if match all the factor's List is enough,
         *     just do the grading and sort;
         * else
         *     get more travel plan with less match degree,
         *     then do the grading and sort.
         * return travelPlanVOList, its size <= 10
         */
        if(travelPlanVOList.size() >= RecommendationConstant.DEFAULT_RECOMMENDATION_SIZE){
            RecommendationSorting.travelPlanSort(travelPlanVOList);
            travelPlanVOList = travelPlanVOList.subList(0, RecommendationConstant.DEFAULT_RECOMMENDATION_SIZE);
        }else {
            List<TravelPlanVO> lessMatchTravelPlanVOList = getLessMatchTravelPlanList(area,scene,season,artificial,climate);
            RecommendationSorting.travelPlanSort(lessMatchTravelPlanVOList);
            if(lessMatchTravelPlanVOList.size() < RecommendationConstant.DEFAULT_RECOMMENDATION_SIZE - travelPlanVOList.size()){
                travelPlanVOList.addAll(lessMatchTravelPlanVOList);
            }else {
                travelPlanVOList.addAll(lessMatchTravelPlanVOList.subList(0,RecommendationConstant.DEFAULT_RECOMMENDATION_SIZE - travelPlanVOList.size()));
            }
        }
        return travelPlanVOList;
    }

    @Override
    public List<TravelResourceItemVO> findTravelResourceItemByTravelSiteId(Integer travelSiteId) {
        TravelSiteBindingItem travelSiteBindingItem = bindingRepository.findOne()
        return null;
    }

    @Deprecated
    private TravelPlanVO transformTravelPlanToTravelPlanVO(Integer newTravelPlanId, List<Integer> travelSiteIds,List<Integer> bindingItemIds){
        TravelPlanVO travelPlanVO = new TravelPlanVO();
        travelPlanVO.setId(newTravelPlanId);
        travelPlanVO.setTravelSiteIds(travelSiteIds);
        travelPlanVO.setTravelSiteBindingItemIds(bindingItemIds);
        fillTravelPlanDetailMap(travelPlanVO);
        return travelPlanVO;
    }


    private List<TravelPlanVO> getLessMatchTravelPlanList(Integer area, Integer scene, Integer season, Integer artificial, Integer climate){
        List<TravelPlan> lessMatchTravelPlanList = new ArrayList<>();
        if(area == 0){
            lessMatchTravelPlanList.addAll(travelPlanRepository.findTravelPlanByTags(0,scene,season,artificial,climate));
        }
        if(scene == 0){
            lessMatchTravelPlanList.addAll(travelPlanRepository.findTravelPlanByTags(area,0,season,artificial,climate));
        }
        if(season == 0){
            lessMatchTravelPlanList.addAll(travelPlanRepository.findTravelPlanByTags(area,scene,0,artificial,climate));
        }
        if(artificial == 0){
            lessMatchTravelPlanList.addAll(travelPlanRepository.findTravelPlanByTags(area,scene,season,0,climate));
        }
        if(climate == 0){
            lessMatchTravelPlanList.addAll(travelPlanRepository.findTravelPlanByTags(area,scene,season,artificial,0));
        }
        return lessMatchTravelPlanList.stream().map(travelPlan -> {
            //find travel plan for existed travel plan database
            return transformTravelPlanToTravelPlanVO(travelPlan,true);
        }).collect(Collectors.toList());
    }


    private TravelPlanVO transformTravelPlanToTravelPlanVO(TravelPlan travelPlan, boolean alreadyExisted){
        TravelPlanVO travelPlanVO = new TravelPlanVO();
        List<Integer> travelSiteIds = JSON.parseArray(travelPlan.getTravelSiteIds(), Integer.class);
        List<Integer> travelSiteBindingItemIds = JSON.parseArray(travelPlan.getTravelSiteBindingItemIds(), Integer.class);
        travelPlanVO.setId(travelPlan.getId());
        travelPlanVO.setTravelSiteIds(travelSiteIds);
        travelPlanVO.setTravelSiteBindingItemIds(travelSiteBindingItemIds);
        fillTravelPlanDetailMap(travelPlanVO);
        calPriceAndCost(travelPlanVO);
        travelPlanVO.setAlreadyExisted(alreadyExisted);
        return travelPlanVO;
    }

    /**
     * calculate price and cost of travel_plan by adding the price and cost of its items one by one.
     * @param travelPlanVO
     * @return
     */
    private TravelPlanVO calPriceAndCost(TravelPlanVO travelPlanVO){
        int price,cost;
        price = cost = 0;
        for(Integer bindingItemId : travelPlanVO.getTravelSiteBindingItemIds()){
            TravelSiteBindingItem travelSiteBindingItem = bindingRepository.findOne(bindingItemId);
            List<Integer> travelResourceItems = JSON.parseArray(travelSiteBindingItem.getTravelResourceItemIds(), Integer.class);
            for(Integer travelResourceItemId : travelResourceItems){
                TravelResourceItem travelResourceItem = travelResourceItemRepository.findOne(travelResourceItemId);
                price += travelResourceItem.getPrice();
                cost += travelResourceItem.getCost();
            }
        }
        travelPlanVO.setPrice(price);
        travelPlanVO.setCost(cost);
        return travelPlanVO;
    }

    private TravelPlanVO fillTravelPlanDetailMap(TravelPlanVO travelPlanVO){
        Map<TravelSite, List<TravelResourceItem>> detailTravelPlanMap = new HashMap<>();
        List<Integer> travelSiteIds = travelPlanVO.getTravelSiteIds();
        List<Integer> bindingItemIds = travelPlanVO.getTravelSiteBindingItemIds();
        Assert.isTrue(travelSiteIds.size() == bindingItemIds.size(), "travelSiteIds size doesn't equal bindingItemIds");
        for(int i = 0; i < travelSiteIds.size(); i++){
            Integer travelSiteId = travelSiteIds.get(i);
            Integer bindingItemId = bindingItemIds.get(i);
            TravelSite travelSite = travelSiteRepository.findOne(travelSiteId);
            TravelSiteBindingItemTemp travelSiteBindingItemTemp = bindingTempRepository.findOne(bindingItemId);
            List<Integer> travelResourceItemIds = JSON.parseArray(travelSiteBindingItemTemp.getTravelResourceItemIds(), Integer.class);
            //find TravelResourceItem from db, and insert it into the travelResourceItemList
            List<TravelResourceItem> travelResourceItemList = new ArrayList<>();
            for(Integer travelResourceItemId : travelResourceItemIds){
                travelResourceItemList.add(travelResourceItemRepository.findOne(travelResourceItemId));
            }
            detailTravelPlanMap.put(travelSite,travelResourceItemList);
        }
        travelPlanVO.setDetailTravelPlanMap(detailTravelPlanMap);
        return travelPlanVO;
    }
}
