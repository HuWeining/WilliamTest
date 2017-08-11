package com.example.demo.algorithm;

import com.example.demo.entity.travelResource.TravelPlan;
import com.example.demo.model.TravelPlanVO;

import java.util.List;
import java.util.Map;

/**
 * Created by huweining on 2017/8/5.
 */
public class EvaluationSorting {

    public static double evaluationTravelPlan(TravelPlanVO travelPlanVO){
        int grossProfit = travelPlanVO.getPrice() - travelPlanVO.getCost();
        double acceptance = travelPlanVO.getAcceptance();
        double userJudgement = travelPlanVO.getUserJudgement();
        double matchDegree = travelPlanVO.getMatchDegree();
        double popularity = travelPlanVO.getPopularity();
        double grade = grossProfit * acceptance * userJudgement * (matchDegree+0.5)/(matchDegree+1) * Math.log10(popularity*10);
        travelPlanVO.setGrade(grade);
        return grade;
        /**
         * if travel_plan is already existed,
         *     search the acceptance and judgement by travel_plan
         * else
         *     choose the lowest acceptance and judgement of travel_plan's travel_resource_item
         */


    }
}
