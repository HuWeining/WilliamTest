package com.example.demo.repository;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by huweining on 2017/6/13.
 */
@Repository
public interface AdminRepository extends CrudRepository<Admin,Integer>{

    Admin findByUsernameAndPassword(String username, String password);

}
