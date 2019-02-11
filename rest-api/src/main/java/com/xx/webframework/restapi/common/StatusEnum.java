package com.xx.webframework.restapi.common;

public enum StatusEnum {

    VALID(1,"有效"),
    UN_VALID(2,"无效");

    private int value;

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    private String message;

    StatusEnum(int value, String message) {
        this.value = value;
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
