package com.seerbit.middleware.erp.middleware.model;

public class ApiResponse {
    private String message;
    private Payload payload;
    private String code;

    public ApiResponse(String message, Payload payload, String code) {
        this.message = message;
        this.payload = payload;
        this.code = code;
    }

    // getters and setters
}



