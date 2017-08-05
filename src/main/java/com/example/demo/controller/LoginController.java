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
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by huweining on 2017/6/13.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{

    @Autowired
    @Qualifier("guestServiceImpl")
    private GuestService guestService;


    @RequestMapping(value = "/loginAdmin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity loginAdmin(String username,String password){
        boolean isSuccess = guestService.loginAdmin(username, password);
        if(isSuccess){
            return this.returnSuccessMsg();
        }else {
            return this.returnFailMsg();
        }

    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public ResponseEntity test(){
        return this.returnSuccessMsg("huweining");

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
