package com.example.demo.repository;

import com.example.demo.entity.travelResource.TravelSite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huweining on 2017/6/13.
 */
@Repository
public interface TravelSiteRepository extends CrudRepository<TravelSite,Integer>{
    List<TravelSite> findByArea(int area);

    List<TravelSite> findByIdIn(List<Integer> ids);

}
