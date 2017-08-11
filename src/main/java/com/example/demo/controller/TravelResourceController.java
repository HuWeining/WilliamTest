package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.TravelResourceItemVO;
import com.example.demo.model.TravelSiteVO;
import com.example.demo.service.travelResource.TravelResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huweining on 2017/6/13.
 */
@Controller
@RequestMapping("/travelResource")
public class TravelResourceController extends BaseController{

    @Autowired
    @Qualifier("travelResourceServiceImpl")
    private TravelResourceService travelResourceService;


    @RequestMapping(value = "/findAllTravelSite")
    @ResponseBody
    public ResponseEntity findAllTravelSite(){
        List<TravelSiteVO> travelPlanVOList = travelResourceService.findAllTravelVOSite();
        return this.returnSuccessMsg(travelPlanVOList);
    }

    @RequestMapping(value = "/findTravelSiteByArea")
    @ResponseBody
    public ResponseEntity findTravelSiteByArea(Integer area){
        List<TravelSiteVO> travelPlanVOList = travelResourceService.findTravelSiteVOByArea(area);
        return this.returnSuccessMsg(travelPlanVOList);
    }

    @RequestMapping(value = "/findAllTravelResourceItems")
    @ResponseBody
    public ResponseEntity findAllTravelResourceItems(){
        List<TravelResourceItemVO> travelResourceItemVOList = travelResourceService.findAllTravelResourceItemVO();
        return this.returnSuccessMsg(travelResourceItemVOList);
    }

    @RequestMapping(value = "/findTravelResourceItemByTravelSiteId")
    @ResponseBody
    public ResponseEntity findTravelResourceItemByTravelSiteId(String travelSiteIdsJson){
        List<Integer> travelSiteIds = JSON.parseArray(travelSiteIdsJson, Integer.class);
        List<TravelResourceItemVO> travelResourceItemVOListTotal = new ArrayList<>();
        for(Integer travelSiteId : travelSiteIds){
            List<TravelResourceItemVO> travelResourceItemVOList = travelResourceService.findTravelResourceItemVOByTravelSiteId(travelSiteId);
            travelResourceItemVOListTotal.addAll(travelResourceItemVOList);
        }
        return this.returnSuccessMsg(travelResourceItemVOListTotal);
    }
}
