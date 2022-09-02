package com.contestapi.contestapo.Exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
