package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.HttpResponseModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by huweining on 2017/6/13.
 */
public abstract class BaseController {
    protected static HttpHeaders headers = new HttpHeaders();
    static {
        headers.add("Content-Type", "application/json; charset=utf-8");
    }

    protected ResponseEntity<String> returnSuccessMsg(){
        HttpResponseModel<String> result = new HttpResponseModel<>();
        result.setSuccess(true);
        result.setMessage("SUCCESS");
        result.setData(null);
        return new ResponseEntity<>(JSON.toJSONString(result), headers, HttpStatus.OK);
    }

    protected <T> ResponseEntity<String> returnSuccessMsg(T t){
        HttpResponseModel<T> result = new HttpResponseModel<T>();
        result.setSuccess(true);
        result.setMessage("SUCCESS");
        result.setData(t);
        return new ResponseEntity<>(JSON.toJSONString(result), headers, HttpStatus.OK);
    }

    protected <T> ResponseEntity<String> returnFailMsg(){
        return this.returnFailMsg(null, null);
    }

    protected <T> ResponseEntity<String> returnFailMsg(String msg){
        return this.returnFailMsg(msg, null);
    }

    protected <T> ResponseEntity<String> returnFailMsg(String msg, T t){
        HttpResponseModel<T> result = new HttpResponseModel<T>();
        result.setSuccess(false);
        result.setMessage(msg);
        result.setData(t);
        return new ResponseEntity<>(JSON.toJSONString(result), headers, HttpStatus.OK);
    }
}
