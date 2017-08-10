package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.algorithm.EvaluationSorting;
import com.example.demo.entity.Guest;
import com.example.demo.model.TravelPlanVO;
import com.example.demo.model.TravelSiteVO;
import com.example.demo.service.GuestService;
import com.example.demo.service.recommendation.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by huweining on 2017/6/13.
 */
@Controller
@RequestMapping("/recommendation")
public class RecommendationController extends BaseController{

    @Autowired
    @Qualifier("recommendationServiceImpl")
    private RecommendationService recommendationService;


    @RequestMapping(value = "/findTravelPlanByTags")
    @ResponseBody
    public ResponseEntity findTravelPlanByTags(Integer area, Integer scene, Integer season, Integer suitAge, Integer category){
        List<TravelPlanVO> travelPlanVOList = recommendationService.findTravelPlanByTags(area, scene, season, suitAge, category, true);
        return this.returnSuccessMsg(travelPlanVOList);
    }

    @RequestMapping(value = "/getRecommendationList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity guest(){
        return this.returnSuccessMsg(recommendationService.getDefaultRecommendationList());
    }

    @RequestMapping(value = "/buildTempTravelPlan", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity buildTempTravelPlan(String travelResourceItemIdsJson, String travelSiteIdsJson){
        List<Integer> travelResourceItemIds = JSON.parseArray(travelResourceItemIdsJson, Integer.class);
        List<Integer> travelSiteIds = JSON.parseArray(travelSiteIdsJson, Integer.class);
        travelSiteIds = new ArrayList<>(new HashSet<>(travelSiteIds));
        TravelPlanVO travelPlanVO = recommendationService.buildTempTravelPlan(travelResourceItemIds, travelSiteIds);
        travelPlanVO.setGrade(EvaluationSorting.evaluationTravelPlan(travelPlanVO));
        return this.returnSuccessMsg(travelPlanVO);

    }

    @RequestMapping(value = "/findTravelResourceItemByTravelSiteId")
    public ResponseEntity findTravelResourceItemByTravelSiteId(int travelSiteId){
        return this.returnSuccessMsg(recommendationService.findTravelResourceItemByTravelSiteId(travelSiteId));
    }

    @RequestMapping(value = "/test3")
    public String test3(){
        return "components/homePage";
    }


}
