package com.xx.webframework.restapi.common;

import lombok.Data;

@Data
public class ResponseData {
    private String code;
    private String messaage;
    private Object data;
}
