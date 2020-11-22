package com.app.AppJava.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MessageResponse {

    @JsonFormat
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
