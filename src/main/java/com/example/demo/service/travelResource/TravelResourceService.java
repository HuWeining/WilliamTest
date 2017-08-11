package com.example.demo.service.travelResource;

import com.example.demo.entity.Guest;
import com.example.demo.entity.travelResource.TravelPlan;
import com.example.demo.entity.travelResource.TravelResourceItem;
import com.example.demo.entity.travelResource.TravelSite;
import com.example.demo.model.TravelPlanVO;
import com.example.demo.model.TravelResourceItemVO;
import com.example.demo.model.TravelSiteVO;

import java.util.List;

/**
 * Created by huweining on 2017/7/23.
 */
public interface TravelResourceService {
    List<TravelSiteVO> findAllTravelVOSite();

    List<TravelSiteVO> findTravelSiteVOByArea(int areaCode);

    List<TravelResourceItemVO> findTravelResourceItemVOByTravelSiteId(int travelSiteId);

    List<TravelResourceItemVO> findAllTravelResourceItemVO();

    TravelSiteVO transformTravelSiteToTravelSiteVO(TravelSite travelSite);

    TravelResourceItemVO transformTravelResourceItemToTravelResourceItemVO(TravelResourceItem travelResourceItem);

    TravelPlanVO transformTravelPlanToTravelPlanVO(TravelPlan travelPlan, boolean alreadyExisted);

    TravelPlan calPriceAndCost(TravelPlan travelPlan);
}
