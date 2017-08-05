package com.example.demo.service;

import com.example.demo.entity.Guest;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;
import com.example.demo.service.StompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by huweining on 2017/7/23.
 */
@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    StompService stompService;

    @Override
    public boolean addGuest(Guest guest) {
        return Objects.isNull(guestRepository.save(guest));
    }

    @Override
    public boolean loginAdmin(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username,password) != null;
    }

    @Override
    public boolean updateGuest(Guest guest) {
        guestRepository.save(guest);
        stompService.notifyUpdateGuest();
        return false;
    }

    @Override
    public List<Guest> guestList() {
        List<Guest> list = new ArrayList<>();
        for (Guest guest:guestRepository.findAll()){
            list.add(guest);
        }
        return list;
    }
}
