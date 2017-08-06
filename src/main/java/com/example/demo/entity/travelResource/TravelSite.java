package com.example.demo.entity.travelResource;

import javax.persistence.*;

/**
 * Created by huweining on 2017/8/10.
 */
@Entity
@Table(name = "TRAVEL_SITE")
public class TravelSite {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "SITE_NAME")
    private String siteName;

    /**
     * area 0000001, 0000010, 0000100, 0001000, 0010000, 0100000,   1000000 represent the area
     *       Japan,   SE Asia, China,   Europe,  America, Australia, Africa  respectively.
     * a area code 0000111 represents a plan includes Japan, SE Asia, China, Europe, store in 7.
     */
    @Column(name = "AREA")
    private Integer area;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }
}
