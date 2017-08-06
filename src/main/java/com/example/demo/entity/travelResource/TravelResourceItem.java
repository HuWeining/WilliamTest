package com.example.demo.entity.travelResource;

import javax.persistence.*;

/**
 * Created by huweining on 2017/8/10.
 */
@Table(name = "TRAVEL_RESOURCE_ITEM")
@Entity
public class TravelResourceItem{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COST")
    private Integer cost;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "TRAVEL_SITE_ID")
    private Integer travelSiteId;

    /**
     * all the fields below are tags of the travel resource item, mainly serves for travel plan and tags search.
     * bit operation & is used to make a combined sign.
     * easy to expand, maintain and modify.
     *
     * Example 1:
     * area 0000001, 0000010, 0000100, 0001000, 0010000, 0100000,   1000000 represent the area
     *       Japan,   SE Asia, China,   Europe,  America, Australia, Africa  respectively.
     * a area code 0000111 represents a plan includes Japan, SE Asia, China, Europe.
     *
     * Example 2:
     * suit_age 0001,  0010,  0100,        1000 represent the age
     *          child, young, middle-aged, elderly respectively.
     * a suit_age code 1111 represents that it suit all ages, stored in 15.
     *
     */
    /**
     * area 0000001, 0000010, 0000100, 0001000, 0010000, 0100000,   1000000 represent the area
     *       Japan,   SE Asia, China,  Europe,  America, Australia, Africa  respectively.
     * a area code 0000111 represents a plan includes Japan, SE Asia, China, Europe, store in 7.
     */
    @Column(name = "AREA")
    private Integer area;

    /**
     * scene 0000001, 0000010,  0000100, 0001000,   0010000, 0100000, 1000000 represent the scene
     *       island,  mountain, prairie, Sea/Ocean, desert,  urban,   remote area respectively.
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

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getScene() {
        return scene;
    }

    public void setScene(Integer scene) {
        this.scene = scene;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getSuitAge() {
        return suitAge;
    }

    public void setSuitAge(Integer suitAge) {
        this.suitAge = suitAge;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
