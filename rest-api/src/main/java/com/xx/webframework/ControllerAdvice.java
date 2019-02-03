package com.xx.webframework;

import com.xx.webframework.restapi.common.ApiErrorCode;
import com.xx.webframework.restapi.common.ApiException;
import com.xx.webframework.restapi.common.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.beans.PropertyEditorSupport;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    /**
     * XSS 过滤
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                String escapeHtml4 = StringEscapeUtils.escapeHtml4(text.trim());
                //特殊处理<br>：不过滤
                if (escapeHtml4 != null&&escapeHtml4.contains("&lt;br&gt;")) {
                    escapeHtml4=escapeHtml4.replace("&lt;br&gt;","<br>");
                }
                setValue(text == null ? null : escapeHtml4);
            }

            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseData handleException(AuthorizationException e, Model model) {
        log.error("",e);
        ResponseData responseData = new ResponseData();
        responseData.setCode(ResponseData.ERROR);
        responseData.setMessage("访问未授权");
        return responseData;
    }
        /**
         * 统一异常处理
         * @param ex
         * @return
         */
    @ExceptionHandler(Exception.class)
    public ResponseData handle(Exception ex) {
        log.error("",ex);
        ResponseData responseData = new ResponseData();
        if(ex instanceof ApiException) {
            ApiException apiException = (ApiException) ex;
            responseData.setCode(apiException.getErrorCode());
            responseData.setMessage(apiException.getErrorMessage());
        }else{
            responseData.setCode(ResponseData.ERROR);
            responseData.setMessage("未知错误");
        }
        return responseData;
    }
}
