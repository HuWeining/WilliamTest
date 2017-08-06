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
        travelResourceItemVO.setArea(TravelResourceItemVO.getStringFromCode(travelResourceItem.getArea(), RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_AREA));
        travelResourceItemVO.setScene(TravelResourceItemVO.getStringFromCode(travelResourceItem.getScene(),RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_SCENE));
        travelResourceItemVO.setSeason(TravelResourceItemVO.getStringFromCode(travelResourceItem.getSeason(),RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_SEASON));
        travelResourceItemVO.setSuitAge(TravelResourceItemVO.getStringFromCode(travelResourceItem.getSuitAge(),RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_SUITAGE));
        travelResourceItemVO.setCategory(TravelResourceItemVO.getStringFromCode(travelResourceItem.getCategory(),RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_CATEGORY));
        return travelResourceItemVO;
    }

    @Override
    public TravelPlanVO transformTravelPlanToTravelPlanVO(TravelPlan travelPlan, boolean alreadyExisted){
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
