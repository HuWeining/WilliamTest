package com.example.demo.service.recommendation.impl;

import com.example.demo.model.CustomerVO;
import com.example.demo.model.TravelPlanVO;
import com.example.demo.model.TravelResourceItemVO;
import com.example.demo.model.TravelSiteVO;
import com.example.demo.repository.*;
import com.example.demo.service.StompService;
import com.example.demo.service.recommendation.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    StompService stompService;

    @Override
    public List<TravelPlanVO> getDefaultRecommendationList() {
        return null;
    }

    @Override
    public TravelPlanVO buildTempTravelPlan(List<TravelSiteVO> route, CustomerVO customer) {

        return null;
    }

    @Override
    public TravelPlanVO findTravelPlan(Integer area, Integer scene, Integer season, Integer artificial, Integer climate) {
        return null;
    }

    @Override
    public List<TravelResourceItemVO> findTravelResourceItemByTravelSiteId(Integer travelSiteId) {
        return null;
    }
}
