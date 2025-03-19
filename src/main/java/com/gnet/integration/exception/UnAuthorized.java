package com.gnet.integration.exception;

public class UnAuthorized extends RuntimeException {

    private String message;

    private String description;

    public UnAuthorized(String message, String description){
        this.message= message;
        this.description= description;

    }
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}