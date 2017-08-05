package com.example.demo.repository;

import com.example.demo.entity.Guest;
import com.example.demo.entity.TravelResource.TravelSite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by huweining on 2017/6/13.
 */
@Repository
public interface TravelSiteRepository extends CrudRepository<TravelSite,Integer>{


}
