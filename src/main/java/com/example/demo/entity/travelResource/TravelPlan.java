package com.example.demo.entity.travelResource;

import javax.persistence.*;

/**
 * Created by huweining on 2017/8/10.
 */
@Entity
@Table(name = "TRAVEL_PLAN")
public class TravelPlan{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    /**
     * TRAVEL_SITE_IDS EXAMPLE: [1,2,5,6,12]
     */
    @Column(name = "TRAVEL_SITE_IDS")
    private String travelSiteIds;

    @Column(name = "TRAVEL_SITE_BINDING_ITEM_IDS")
    private String travelSiteBindingItemIds;

    @Column(name = "COST")
    private Integer cost;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "ALREADY_EXISTED")
    private Boolean alreadyExisted;

    /**
     * all the fields below are tags of the travel resource item, mainly serves for travel plan and tags search.
     * bit operation & is used to make a combined sign.
     * travel_plan's code is its travelResourceItems' code's AND operation's result
     *
     * Example 1:
     * category 000001,                 000010, 000100,   001000    010000, 100000 represent the category
     *          historical and culture, museum, religion, festival, nature, animal respectively.
     *
     * a travel plan has 3 travelResourceItems and their category code are 000010,000100,000010;
     * The travel plan's category code is 000110, store in 6.
     *
     */

    /**
     * area 0000001, 0000010, 0000100, 0001000, 0010000, 0100000,   1000000 represent the area
     *       Japan,   SE Asia, China,   Europe,  America, Australia, Africa  respectively.
     * a area code 0000111 represents a plan includes Japan, SE Asia, China, Europe.
     */
    @Column(name = "AREA")
    private Integer area;

    /**
     * scene 0000001, 0000010,  0000100, 0001000,   0010000, 0100000, 1000000 represent the scene
     *       island,  mountain, prairie, sea/Ocean, desert,  urban,   remote area respectively.
     */
    @Column(name = "SCENE")
    private Integer scene;

    /**
     * season 0001,   0010,   0100,   1000 represent the season
     *        spring, summer, autumn, winter respectively.
     */
    @Column(name = "SEASON")
    private Integer season;

    /**
     * suit_age 0001,  0010,  0100,        1000 represent the suit age
     *          child, young, middle-aged, elderly respectively.
     */
    @Column(name = "SUIT_AGE")
    private Integer suitAge;

    /**
     * category 0000001,    0000010, 0000100,  0001000   0010000, 0100000, 1000000 represent the category
     *          historical, museum,  religion, festival, nature,  animal,  building respectively.
     */
    @Column(name = "CATEGORY")
    private Integer category;

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

    public String getTravelSiteIds() {
        return travelSiteIds;
    }

    public void setTravelSiteIds(String travelSiteIds) {
        this.travelSiteIds = travelSiteIds;
    }

    public String getTravelSiteBindingItemIds() {
        return travelSiteBindingItemIds;
    }

    public void setTravelSiteBindingItemIds(String travelSiteBindingItemIds) {
        this.travelSiteBindingItemIds = travelSiteBindingItemIds;
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
}
