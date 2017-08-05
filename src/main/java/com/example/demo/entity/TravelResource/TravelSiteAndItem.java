package com.example.demo.entity.TravelResource;

import javax.persistence.*;
import java.util.List;

/**
 * Created by huweining on 2017/8/10.
 */
@Entity
@Table(name = "TRAVEL_SITE_AND_ITEM")
public class TravelSiteAndItem {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "TRAVEL_SITE_ID")
    Integer travelSiteId;

    @Column(name = "TRAVEL_RESOURCE_ITEM_IDS")
    List<Integer> travelResourceItemIds;

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

    public List<Integer> getTravelResourceItemIds() {
        return travelResourceItemIds;
    }

    public void setTravelResourceItemIds(List<Integer> travelResourceItemIds) {
        this.travelResourceItemIds = travelResourceItemIds;
    }
}
