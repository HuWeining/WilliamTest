package com.example.demo.controller;

import com.example.demo.entity.Guest;
import com.example.demo.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * Created by huweining on 2017/6/13.
 */
@Controller
@RequestMapping("/guest")
public class GuestManagementController extends BaseController{

    @Autowired
    @Qualifier("guestServiceImpl")
    private GuestService guestService;


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addGuest(Guest guest){
        boolean isSuccess = guestService.addGuest(guest);
        if(isSuccess){
            return this.returnSuccessMsg();
        }else {
            return this.returnFailMsg();
        }

    }

    @RequestMapping(value = "/guestList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity guest(){
        return this.returnSuccessMsg(guestService.guestList());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity test(Guest guest){
        guestService.updateGuest(guest);
        return this.returnSuccessMsg("[]");

    }

    @RequestMapping(value = "/test2")
    public ModelAndView test2(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        return modelAndView;
    }

    @RequestMapping(value = "/test3")
    public String test3(){
        return "components/homePage";
    }


}
