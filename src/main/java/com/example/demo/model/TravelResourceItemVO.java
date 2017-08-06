package com.example.demo.model;

import com.example.demo.utilsAndConsants.RecommendationConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huweining on 2017/8/5.
 */
public class TravelResourceItemVO {

    private Integer id;

    private String name;

    private String description;

    private Integer cost;

    private Integer price;

    private Integer travelSiteId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getTravelSiteId() {
        return travelSiteId;
    }

    public void setTravelSiteId(Integer travelSiteId) {
        this.travelSiteId = travelSiteId;
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

    public static List<String> getStringFromCode(int code, int type){
        List<String> list = new ArrayList<>();
        switch (type){
            case RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_AREA :
                if((code&1) == 1){
                    list.add("Japan");
                }
                if((code&3) == 1){
                    list.add("SE Asia");
                }
                if((code&7) == 1){
                    list.add("China");
                }
                if((code&15) == 1){
                    list.add("Europe");
                }
                if((code&31) == 1){
                    list.add("America");
                }
                if((code&63) == 1){
                    list.add("Australia");
                }
                if((code&127) == 1){
                    list.add("Africa");
                }
                break;
            case RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_SCENE :
                if((code&1) == 1){
                    list.add("island");
                }
                if((code&3) == 1){
                    list.add("mountain");
                }
                if((code&7) == 1){
                    list.add("prairie");
                }
                if((code&15) == 1){
                    list.add("Sea/Ocean");
                }
                if((code&31) == 1){
                    list.add("desert");
                }
                if((code&63) == 1){
                    list.add("urban");
                }
                if((code&127) == 1){
                    list.add("remote area");
                }
                break;
            case RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_SEASON :
                if((code&1) == 1){
                    list.add("spring");
                }
                if((code&3) == 1){
                    list.add("summer");
                }
                if((code&7) == 1){
                    list.add("autumn");
                }
                if((code&15) == 1){
                    list.add("winter");
                }
                break;
            case RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_SUITAGE :
                if((code&1) == 1){
                    list.add("child");
                }
                if((code&3) == 1){
                    list.add("young");
                }
                if((code&7) == 1){
                    list.add("middle-aged");
                }
                if((code&15) == 1){
                    list.add("elderly");
                }
                break;
            case RecommendationConstant.TRAVEL_RESOURCE_ITEM_FIELD_TYPE_CATEGORY :
                if((code&1) == 1){
                    list.add("historical");
                }
                if((code&3) == 1){
                    list.add("museum");
                }
                if((code&7) == 1){
                    list.add("religion");
                }
                if((code&15) == 1){
                    list.add("festival");
                }
                if((code&31) == 1){
                    list.add("nature");
                }
                if((code&63) == 1){
                    list.add("animal");
                }
                if((code&127) == 1){
                    list.add("building");
                }
                break;
            default:
                break;

        }
        return list;
    }
}
