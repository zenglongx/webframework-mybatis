package com.xx.webframework.restapi.exception;


import com.xx.webframework.restapi.common.ApiErrorCode;
import com.xx.webframework.restapi.common.ApiException;

public class AccountPasswordErrorApiException extends ApiException {
    public AccountPasswordErrorApiException(){
        super(ApiErrorCode.ACCOUNT_PASSWORD_ERROR,"帐号或密码错误",null);
    }

    public AccountPasswordErrorApiException(String errorMessage, Object data){
        super(ApiErrorCode.ACCOUNT_PASSWORD_ERROR,errorMessage,data);
    }
}
