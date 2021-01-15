package com.example.admin.common.R;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class R implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonIgnore
    private Object[] msgParams;

    /**
     * getter & setters
     */
    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object[] getMsgParams() {
        return msgParams;
    }

    public void setMsgParams(Object[] msgParams) {
        this.msgParams = msgParams;
    }

    @JsonIgnore
    public String getI18nMessage() {
        return "r.status." + getCode();
    }

    /**
     * methods for external calling
     */
    public static R status(HttpStatus status) {
        R r = new R();
        r.setCode(status.value());
        return r;
    }

    public static R status(RStatus status) {
        R r = new R();
        r.setCode(status.getCode());
        return r;
    }

    public static R status(RStatus status, Object[] msgParams) {
        R r = new R();
        r.setCode(status.getCode());
        r.setMsgParams(msgParams);
        return r;
    }

    public R data(Object data) {
        setData(data);
        return this;
    }
}