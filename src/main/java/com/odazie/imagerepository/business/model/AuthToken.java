package com.odazie.imagerepository.business.model;

import io.swagger.annotations.ApiModelProperty;

public class AuthToken {
    @ApiModelProperty(notes = "'Bearer Token' please copy with the Bearer spring and input in the AUTHORIZE button field ")
    private String token;

    public AuthToken(){

    }

    public AuthToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
