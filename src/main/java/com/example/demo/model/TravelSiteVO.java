package com.example.demo.model;

import java.util.List;

/**
 * Created by huweining on 2017/8/5.
 */
public class TravelSiteVO {

    private Integer id;

    private String siteName;

    private List<Integer> travelResourceItemVOIds;

    private String area;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public List<Integer> getTravelResourceItemVOIds() {
        return travelResourceItemVOIds;
    }

    public void setTravelResourceItemVOIds(List<Integer> travelResourceItemVOIds) {
        this.travelResourceItemVOIds = travelResourceItemVOIds;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public static String getAreaStringFromCode(int areaCode){
        switch (areaCode){
            case 1:
                return "Japan";
            case 3:
                return "SE Asia";
            case 7:
                return "China";
            case 15:
                return "Europe";
            case 31:
                return "America";
            case 63:
                return "Australia";
            case 127:
                return "Africa";
            default:
                return "";
        }
    }
}
