package com.example.demo.repository;

import com.example.demo.entity.TravelResource.TravelResourceItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by huweining on 2017/6/13.
 */
@Repository
public interface TravelResourceItemRepository extends CrudRepository<TravelResourceItem,Integer>{



}
