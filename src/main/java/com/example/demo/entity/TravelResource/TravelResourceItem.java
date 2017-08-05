package com.example.demo.entity.TravelResource;

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

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COST")
    private Integer cost;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "TRAVEL_SITE_ID")
    private Integer travelSiteId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
