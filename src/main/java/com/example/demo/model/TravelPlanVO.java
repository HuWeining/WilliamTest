package com.example.demo.model;


import com.example.demo.entity.travelResource.TravelResourceItem;
import com.example.demo.entity.travelResource.TravelSite;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
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

    private List<TravelSiteAndTravelResourceItemList> travelSiteAndTravelResourceItemLists;

    private List<TravelSiteAndTravelResourceItemList> promotionItemLists;

    private Integer cost;

    private Integer price;

    private Boolean alreadyExisted;

    private String route;

    private Double popularity;

    private Double acceptance;

    private Double userJudgement;
    /**
     * matchDegree default value = 1
     */
    private double matchDegree = 1;

    /**
     * grade is calculated by com.example.demo.algorithm.EvaluationSorting#evaluationTravelPlan(com.example.demo.model.TravelPlanVO)
     * and used in com.example.demo.algorithm.RecommendationSorting#travelPlanSort(java.util.List)
     */
    private double grade;


    /**
     * area 0000001, 0000010, 0000100, 0001000, 0010000, 0100000,   1000000 represent the area
     *       Japan,  SE Asia, China,   Europe, America, Australia, Africa  respectively.
     *      (1,      2,       4,       8,      16,      32,        64)
     * a area code 0000111 represents a plan includes Japan, SE Asia, China, Europe, store in 7.
     */
    private List<String> area;

    /**
     * scene 0000001, 0000010,  0000100, 0001000,   0010000, 0100000, 1000000 represent the scene
     *       island,  mountain, prairie, sea/Ocean, desert,  urban,   remote area respectively.
     *       (1,      2,        4,       8,         16,      32,      64)
     */
    private List<String> scene;

    /**
     * season 0001,   0010,   0100,   1000 represent the season
     *        spring, summer, autumn, winter respectively.
     *        (1,     2,      4,      8)
     */
    private List<String> season;

    /**
     * suit_age 0001,  0010,  0100,        1000 represent the suit age
     *          child, young, middle-aged, elderly respectively.
     *          (1,    2,     4,           8)
     */
    private List<String> suitAge;

    /**
     * category 0000001,    0000010, 0000100,  0001000   0010000, 0100000, 1000000 represent the category
     *          historical, museum,  religion, festival, nature,  animal,  building respectively.
     *          (1,         2,       4,        8,        16,      32,      64)
     */
    private List<String> category;

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

    public List<TravelSiteAndTravelResourceItemList> getTravelSiteAndTravelResourceItemLists() {
        return travelSiteAndTravelResourceItemLists;
    }

    public void setTravelSiteAndTravelResourceItemLists(List<TravelSiteAndTravelResourceItemList> travelSiteAndTravelResourceItemLists) {
        this.travelSiteAndTravelResourceItemLists = travelSiteAndTravelResourceItemLists;
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

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
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

    public static class TravelSiteAndTravelResourceItemList{
        TravelSiteVO travelSiteVO;
        List<TravelResourceItemVO> travelResourceItemVOList;

        public TravelSiteVO getTravelSiteVO() {
            return travelSiteVO;
        }

        public void setTravelSiteVO(TravelSiteVO travelSiteVO) {
            this.travelSiteVO = travelSiteVO;
        }

        public List<TravelResourceItemVO> getTravelResourceItemVOList() {
            return travelResourceItemVOList;
        }

        public void setTravelResourceItemVOList(List<TravelResourceItemVO> travelResourceItemVOList) {
            this.travelResourceItemVOList = travelResourceItemVOList;
        }
    }

    public List<String> getArea() {
        return area;
    }

    public void setArea(List<String> area) {
        this.area = area;
    }

    public List<String> getScene() {
        return scene;
    }

    public void setScene(List<String> scene) {
        this.scene = scene;
    }

    public List<String> getSeason() {
        return season;
    }

    public void setSeason(List<String> season) {
        this.season = season;
    }

    public List<String> getSuitAge() {
        return suitAge;
    }

    public void setSuitAge(List<String> suitAge) {
        this.suitAge = suitAge;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<TravelSiteAndTravelResourceItemList> getPromotionItemLists() {
        return promotionItemLists;
    }

    public void setPromotionItemLists(List<TravelSiteAndTravelResourceItemList> promotionItemLists) {
        this.promotionItemLists = promotionItemLists;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Double getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(Double acceptance) {
        this.acceptance = acceptance;
    }

    public Double getUserJudgement() {
        return userJudgement;
    }

    public void setUserJudgement(Double userJudgement) {
        this.userJudgement = userJudgement;
    }
}
