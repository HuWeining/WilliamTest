package com.example.demo.service.travelResource.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.Guest;
import com.example.demo.entity.travelResource.*;
import com.example.demo.model.TravelPlanVO;
import com.example.demo.model.TravelResourceItemVO;
import com.example.demo.model.TravelSiteVO;
import com.example.demo.repository.*;
import com.example.demo.service.GuestService;
import com.example.demo.service.StompService;
import com.example.demo.service.travelResource.TravelResourceService;
import com.example.demo.utilsAndConsants.RecommendationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by huweining on 2017/7/23.
 */
@Service
public class TravelResourceServiceImpl implements TravelResourceService {

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
    public List<TravelSiteVO> findAllTravelVOSite() {
        List<TravelSite> travelSiteList = (ArrayList)travelSiteRepository.findAll();
        List<TravelSiteVO> travelPlanVOList = travelSiteList.stream().map(
            travelSite -> {
                return transformTravelSiteToTravelSiteVO(travelSite);
            }
        ).collect(Collectors.toList());
        return travelPlanVOList;
    }

    @Override
    public List<TravelSiteVO> findTravelSiteVOByArea(int areaCode) {
        return travelSiteRepository.findByArea(areaCode).stream().map(
                travelSite -> {
                    return transformTravelSiteToTravelSiteVO(travelSite);
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<TravelResourceItemVO> findTravelResourceItemVOByTravelSiteId(int travelSiteId) {
        return travelResourceItemRepository.findByTravelSiteId(travelSiteId).stream().map(
                travelResourceItem -> {
                    return transformTravelResourceItemToTravelResourceItemVO(travelResourceItem);
                }
        ).collect(Collectors.toList());
    }

    @Override
    public TravelSiteVO transformTravelSiteToTravelSiteVO(TravelSite travelSite) {
        TravelSiteVO travelSiteVO = new TravelSiteVO();
        travelSiteVO.setId(travelSite.getId());
        travelSiteVO.setSiteName(travelSite.getSiteName());
        travelSiteVO.setArea(TravelSiteVO.getAreaStringFromCode(travelSite.getArea()));
        return travelSiteVO;
    }

    @Override
    public TravelResourceItemVO transformTravelResourceItemToTravelResourceItemVO(TravelResourceItem travelResourceItem){
        TravelResourceItemVO travelResourceItemVO = new TravelResourceItemVO();
        travelResourceItemVO.setId(travelResourceItem.getId());
        travelResourceItemVO.setName(travelResourceItem.getName());
        travelResourceItemVO.setDescription(travelResourceItem.getDescription());
        travelResourceItemVO.setCost(travelResourceItem.getCost());
        travelResourceItemVO.setPrice(travelResourceItem.getPrice());
        travelResourceItemVO.setTravelSiteName(travelSiteRepository.findOne(travelResourceItem.getTravelSiteId()).getSiteName());
        travelResourceItemVO.setTravelSiteId(travelResourceItem.getTravelSiteId());
        travelResourceItemVO.setArea(TravelResourceItemVO.getStringFromCode(travelResourceItem.getArea(), RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_AREA));
        travelResourceItemVO.setScene(TravelResourceItemVO.getStringFromCode(travelResourceItem.getScene(),RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_SCENE));
        travelResourceItemVO.setSeason(TravelResourceItemVO.getStringFromCode(travelResourceItem.getSeason(),RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_SEASON));
        travelResourceItemVO.setSuitAge(TravelResourceItemVO.getStringFromCode(travelResourceItem.getSuitAge(),RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_SUITAGE));
        travelResourceItemVO.setCategory(TravelResourceItemVO.getStringFromCode(travelResourceItem.getCategory(),RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_CATEGORY));
        return travelResourceItemVO;
    }

    /**
     *
     * @param travelPlan
     * @param alreadyExisted
     * @return
     */
    @Override
    public TravelPlanVO transformTravelPlanToTravelPlanVO(TravelPlan travelPlan, boolean alreadyExisted){
        TravelPlanVO travelPlanVO = new TravelPlanVO();
        List<Integer> travelSiteIds = JSON.parseArray(travelPlan.getTravelSiteIds(), Integer.class);
        List<Integer> travelSiteBindingItemIds = JSON.parseArray(travelPlan.getTravelSiteBindingItemIds(), Integer.class);
        travelPlanVO.setId(travelPlan.getId());
        travelPlanVO.setName(travelPlan.getName());
        travelPlanVO.setTravelSiteIds(travelSiteIds);
        travelPlanVO.setTravelSiteBindingItemIds(travelSiteBindingItemIds);
        travelPlanVO.setPrice(travelPlan.getPrice());
        travelPlanVO.setCost(travelPlan.getCost());
        travelPlanVO.setArea(TravelResourceItemVO.getStringFromCode(travelPlan.getArea(), RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_AREA));
        travelPlanVO.setScene(TravelResourceItemVO.getStringFromCode(travelPlan.getScene(), RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_SCENE));
        travelPlanVO.setSeason(TravelResourceItemVO.getStringFromCode(travelPlan.getSeason(), RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_SEASON));
        travelPlanVO.setSuitAge(TravelResourceItemVO.getStringFromCode(travelPlan.getSuitAge(), RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_SUITAGE));
        travelPlanVO.setCategory(TravelResourceItemVO.getStringFromCode(travelPlan.getCategory(), RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_CATEGORY));
        fillTravelPlanDetailMap(travelPlanVO);
        travelPlanVO.setAlreadyExisted(alreadyExisted);
        return travelPlanVO;
    }

    /**
     * calculate price and cost of travel_plan by adding the price and cost of its items one by one.
     * @param travelPlan
     * @return
     */
    @Override
    public TravelPlan calPriceAndCost(TravelPlan travelPlan){
        int price,cost;
        price = cost = 0;
        for(Integer bindingItemId : JSON.parseArray(travelPlan.getTravelSiteBindingItemIds(), Integer.class)){
            TravelSiteBindingItemTemp travelSiteBindingItem = bindingTempRepository.findOne(bindingItemId);
            List<Integer> travelResourceItems = JSON.parseArray(travelSiteBindingItem.getTravelResourceItemIds(), Integer.class);
            for(Integer travelResourceItemId : travelResourceItems){
                TravelResourceItem travelResourceItem = travelResourceItemRepository.findOne(travelResourceItemId);
                price += travelResourceItem.getPrice();
                cost += travelResourceItem.getCost();
            }
        }
        travelPlan.setPrice(price);
        travelPlan.setCost(cost);
        return travelPlan;
    }

    private TravelPlanVO fillTravelPlanDetailMap(TravelPlanVO travelPlanVO){
        List<TravelPlanVO.TravelSiteAndTravelResourceItemList> travelSiteAndTravelResourceItemLists = new ArrayList<>();
        List<Integer> travelSiteIds = travelPlanVO.getTravelSiteIds();
        List<Integer> bindingItemIds = travelPlanVO.getTravelSiteBindingItemIds();
        Assert.isTrue(travelSiteIds.size() == bindingItemIds.size(), "travelSiteIds size doesn't equal bindingItemIds");
        String route = "";
        for(int i = 0; i < travelSiteIds.size(); i++){
            Integer travelSiteId = travelSiteIds.get(i);
            Integer bindingItemId = bindingItemIds.get(i);
            TravelSite travelSite = travelSiteRepository.findOne(travelSiteId);
            route += travelSite.getSiteName()+"-";
            TravelSiteBindingItemTemp travelSiteBindingItemTemp = bindingTempRepository.findOne(bindingItemId);
            List<Integer> travelResourceItemIds = JSON.parseArray(travelSiteBindingItemTemp.getTravelResourceItemIds(), Integer.class);
            //find TravelResourceItem from db, and insert it into the travelResourceItemList
            List<TravelResourceItem> travelResourceItemList = new ArrayList<>();
            for(Integer travelResourceItemId : travelResourceItemIds){
                travelResourceItemList.add(travelResourceItemRepository.findOne(travelResourceItemId));
            }
            TravelPlanVO.TravelSiteAndTravelResourceItemList travelPlanDetialItem = new TravelPlanVO.TravelSiteAndTravelResourceItemList();
            travelPlanDetialItem.setTravelSite(travelSite);
            travelPlanDetialItem.setTravelResourceItemList(travelResourceItemList);
            travelSiteAndTravelResourceItemLists.add(travelPlanDetialItem);
        }
        travelPlanVO.setRoute(route.substring(0,route.length()-1));
        travelPlanVO.setTravelSiteAndTravelResourceItemLists(travelSiteAndTravelResourceItemLists);
        return travelPlanVO;
    }
}
