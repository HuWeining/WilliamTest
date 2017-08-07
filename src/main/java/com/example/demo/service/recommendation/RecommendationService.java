package com.example.demo.service.recommendation;

import com.example.demo.model.CustomerVO;
import com.example.demo.model.TravelPlanVO;
import com.example.demo.model.TravelResourceItemVO;
import com.example.demo.model.TravelSiteVO;

import java.util.List;

/**
 * Created by huweining on 2017/7/23.
 */
public interface RecommendationService {
    List<TravelPlanVO> getDefaultRecommendationList();

    TravelPlanVO buildTempTravelPlan(List<Integer> travelResourceItemIds, List<Integer> travelSiteIds);

    List<TravelPlanVO> findTravelPlanByTags(Integer area, Integer scene, Integer season, Integer suitAge, Integer category);

    List<TravelResourceItemVO> findTravelResourceItemByTravelSiteId(Integer travelSiteId);
}
