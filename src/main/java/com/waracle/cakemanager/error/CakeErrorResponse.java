package com.waracle.cakemanager.error;

public class CakeErrorResponse {

    private String error;

    public CakeErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

}
