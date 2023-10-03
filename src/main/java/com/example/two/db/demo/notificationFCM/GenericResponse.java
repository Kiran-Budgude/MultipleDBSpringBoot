package com.example.two.db.demo.notificationFCM;

import org.springframework.http.HttpStatus;

public class GenericResponse {

    private HttpStatus status;
    private int code;
    private String message;

    private Object response;
    private Object errros;


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Object getErrros() {
        return errros;
    }

    public void setErrros(Object errros) {
        this.errros = errros;
    }
}
