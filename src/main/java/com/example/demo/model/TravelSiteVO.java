package com.example.demo.model;

import java.util.List;

/**
 * Created by huweining on 2017/8/5.
 */
public class TravelSiteVO {

    private Integer id;

    private String siteName;

    List<TravelResourceItemVO> resourceItemVOList;

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

    public List<TravelResourceItemVO> getResourceItemVOList() {
        return resourceItemVOList;
    }

    public void setResourceItemVOList(List<TravelResourceItemVO> resourceItemVOList) {
        this.resourceItemVOList = resourceItemVOList;
    }
}
