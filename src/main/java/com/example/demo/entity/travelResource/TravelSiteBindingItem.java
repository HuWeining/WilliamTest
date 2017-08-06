package com.example.demo.entity.travelResource;

import javax.persistence.*;

/**
 * Created by huweining on 2017/8/10.
 */
@Entity
@Table(name = "TRAVEL_SITE_BINDING_ITEM")

/**
 * this table store all the company's travel resource
 */
public class TravelSiteBindingItem {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "TRAVEL_SITE_ID")
    Integer travelSiteId;

    /**
     * TRAVEL_RESOURCE_ITEM_ID EXAMPLE: [1,2,5,6,12]
     */
    @Column(name = "TRAVEL_RESOURCE_ITEM_IDS")
    String travelResourceItemIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTravelSiteId() {
        return travelSiteId;
    }

    public void setTravelSiteId(Integer travelSiteId) {
        this.travelSiteId = travelSiteId;
    }

    public String getTravelResourceItemIds() {
        return travelResourceItemIds;
    }

    public void setTravelResourceItemIds(String travelResourceItemIds) {
        this.travelResourceItemIds = travelResourceItemIds;
    }
}
