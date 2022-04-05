package com.example.spring.aop.dto;

public class ResultResponse {
    private Object data;

    public ResultResponse(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
