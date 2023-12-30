package com.mohamed.task.customer;

public class ApiResponse {
    private String message;
    private boolean success;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
    
    public boolean isSuccess() {
    	return success;
    }
    
    public String getMessage() {
    	return message;
    }
    
    public void setSuccess(boolean success) {
    	this.success = success;
    }
    
    public void settMessage(String message) {
    	this.message = message;
    }
}
