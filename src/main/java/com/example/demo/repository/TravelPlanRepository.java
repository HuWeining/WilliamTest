package com.example.demo.repository;

import com.example.demo.entity.Guest;
import com.example.demo.entity.TravelResource.TravelPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by huweining on 2017/6/13.
 */
@Repository
public interface TravelPlanRepository extends CrudRepository<TravelPlan,Integer>{



}
