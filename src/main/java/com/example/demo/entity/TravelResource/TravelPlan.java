package com.example.demo.entity.TravelResource;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "TRAVEL_SITE_IDS")
    List<Integer> travelSiteIds;

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
}
