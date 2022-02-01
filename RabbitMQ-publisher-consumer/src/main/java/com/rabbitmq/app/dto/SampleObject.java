package com.rabbitmq.app.dto;

public class SampleObject {
    private String message;

    public SampleObject() {
    }

    public SampleObject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SampleObject{" +
                "message='" + message + '\'' +
                '}';
    }
}
