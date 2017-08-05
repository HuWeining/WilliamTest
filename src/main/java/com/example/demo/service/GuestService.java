package com.example.demo.service;

import com.example.demo.entity.Guest;

import java.util.List;

/**
 * Created by huweining on 2017/7/23.
 */
public interface GuestService {
    boolean addGuest(Guest guest);
    boolean loginAdmin(String username, String password);
    boolean updateGuest(Guest guest);
    List<Guest> guestList();
}
