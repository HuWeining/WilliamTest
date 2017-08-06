package com.example.demo.model;


import com.example.demo.entity.travelResource.TravelResourceItem;
import com.example.demo.entity.travelResource.TravelSite;

import java.util.List;
import java.util.Map;

/**
 * Created by huweining on 2017/8/5.
 */
public class TravelPlanVO{

    private Integer id;

    private String name;

    private List<Integer> travelSiteIds;

    private List<Integer> travelSiteBindingItemIds;

    private Map<TravelSite, List<TravelResourceItem>> detailTravelPlanMap;

    private Integer cost;

    private Integer price;

    private Boolean alreadyExisted;

    /**
     * matchDegree default value = 1
     */
    private double matchDegree = 1;

    /**
     * grade is calculated by com.example.demo.algorithm.EvaluationSorting#evaluationTravelPlan(com.example.demo.model.TravelPlanVO)
     * and used in com.example.demo.algorithm.RecommendationSorting#travelPlanSort(java.util.List)
     */
    private double grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getTravelSiteIds() {
        return travelSiteIds;
    }

    public void setTravelSiteIds(List<Integer> travelSiteIds) {
        this.travelSiteIds = travelSiteIds;
    }

    public List<Integer> getTravelSiteBindingItemIds() {
        return travelSiteBindingItemIds;
    }

    public void setTravelSiteBindingItemIds(List<Integer> travelSiteBindingItemIds) {
        this.travelSiteBindingItemIds = travelSiteBindingItemIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<TravelSite, List<TravelResourceItem>> getDetailTravelPlanMap() {
        return detailTravelPlanMap;
    }

    public void setDetailTravelPlanMap(Map<TravelSite, List<TravelResourceItem>> detailTravelPlanMap) {
        this.detailTravelPlanMap = detailTravelPlanMap;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getAlreadyExisted() {
        return alreadyExisted;
    }

    public void setAlreadyExisted(Boolean alreadyExisted) {
        this.alreadyExisted = alreadyExisted;
    }

    public double getMatchDegree() {
        return matchDegree;
    }

    public void setMatchDegree(double matchDegree) {
        this.matchDegree = matchDegree;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
