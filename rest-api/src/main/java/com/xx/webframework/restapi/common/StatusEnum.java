package com.xx.webframework.restapi.common;

public enum StatusEnum {

    VALID(1,"有效"),
    UN_VALID(2,"无效");

    private int code;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private String message;

    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    static StatusEnum fromCode(int code){
        switch (code){
            case 1:
                return VALID;
            case 2:
                return UN_VALID;
        }
        return null;
    }
}
