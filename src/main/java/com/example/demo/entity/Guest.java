package com.example.demo.entity;

import javax.persistence.*;

/**
 * Created by huweining on 2017/6/13.
 */
@Entity
@Table(name = "GUEST")
public class Guest {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TRAVEL_PLAN_ID")
    private Integer travelPlanId;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTravelPlanId() {
        return travelPlanId;
    }

    public void setTravelPlanId(Integer travelPlanId) {
        this.travelPlanId = travelPlanId;
    }
}
