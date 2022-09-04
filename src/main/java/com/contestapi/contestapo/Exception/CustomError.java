package com.contestapi.contestapo.Exception;


import lombok.Data;

@Data
public class CustomError extends RuntimeException {

    private String Error;
    private String Description;

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public CustomError(String error, String description) {
        this.Error = error;
        this.Description = description;
    }
}
