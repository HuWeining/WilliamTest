package com.example.demo.algorithm;

import com.example.demo.model.TravelPlanVO;

import java.util.Comparator;
import java.util.List;

/**
 * Created by huweining on 2017/8/5.
 */
public class RecommendationSorting {

    public static void travelPlanSort(List<TravelPlanVO> travelPlanList){

        travelPlanList.sort(new Comparator<TravelPlanVO>() {
            @Override
            public int compare(TravelPlanVO o1, TravelPlanVO o2) {
                double grade1 = EvaluationSorting.evaluationTravelPlan(o1);
                double grade2 = EvaluationSorting.evaluationTravelPlan(o2);
                double subtract =  grade1 - grade2;
                if(subtract > 0)
                    return -1;
                else if(subtract == 0)
                    return 0;
                else
                    return 1;
            }
        });
    }
}
