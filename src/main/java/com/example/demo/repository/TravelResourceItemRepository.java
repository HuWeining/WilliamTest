package com.example.demo.repository;

import com.example.demo.entity.travelResource.TravelResourceItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huweining on 2017/6/13.
 */
@Repository
public interface TravelResourceItemRepository extends CrudRepository<TravelResourceItem,Integer>{

    List<TravelResourceItem> findByTravelSiteId(int travelSiteId);

    List<TravelResourceItem> findByIdIn(List<Integer> ids);

}
