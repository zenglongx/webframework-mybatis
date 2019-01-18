package com.xx.webframework.restapi.common;

import lombok.Data;

@Data
public class ResponseData {

    public static final String SUCCESS = "0";
    public static final String ERROR = "-1";

    private String code;
    private String messaage;
    private Object data;
}
