package com.xx.webframework.restapi.common;


public class UserNotFoundApiException extends ApiException {
    public UserNotFoundApiException(){
        super(ApiErrorCode.NOT_FOUND_USER,"用户不存在",null);
    }

    public UserNotFoundApiException(String errorMessage, Object data){
        super(ApiErrorCode.NOT_FOUND_USER,errorMessage,data);
    }
}
