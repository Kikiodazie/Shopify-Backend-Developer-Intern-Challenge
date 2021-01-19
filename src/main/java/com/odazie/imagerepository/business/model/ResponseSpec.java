package com.odazie.imagerepository.business.model;

public class ResponseSpec {
    private String message;

    public ResponseSpec(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
