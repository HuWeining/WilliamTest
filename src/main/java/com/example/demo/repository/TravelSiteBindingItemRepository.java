 package com.example.demo.repository;

import com.example.demo.entity.travelResource.TravelSite;
import com.example.demo.entity.travelResource.TravelSiteBindingItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by huweining on 2017/6/13.
 */
@Repository
public interface TravelSiteBindingItemRepository extends CrudRepository<TravelSiteBindingItem,Integer>{
    TravelSiteBindingItem findByTravelSiteId(Integer travelSiteId);

}
