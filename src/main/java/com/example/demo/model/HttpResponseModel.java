package com.example.demo.model;

/**
 * Created by huweining on 2017/6/13.
 */
public class HttpResponseModel <T>{

    private Boolean isSuccess;
    private String message;
    private String updateTime;
    private T data;

    public HttpResponseModel() {
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
