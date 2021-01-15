package com.example.admin.common.R;

public class RException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private RStatus status;

    private Object data;

    private Object[] msgParams;

    public RStatus getStatus() {
        return status;
    }

    public void setStatus(RStatus status) {
        this.status = status;
    }

    public RException() {
    }

    public RException(RStatus status) {
        super();
        this.status = status;
    }

    public RException(RStatus status, Object[] msgParams) {
        super();
        this.status = status;
        this.msgParams = msgParams;
    }

    public RException(RStatus status, String message) {
        super(message);
        this.status = status;
    }

    public RException(String message) {
        super(message);
    }

    public RException(Throwable cause) {
        super(cause);
    }

    public RException(String message, Throwable cause) {
        super(message, cause);
    }

    public RException(String message, Throwable cause,
                      boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RException setData(Object data) {
        this.data = data;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Object[] getMsgParams() {
        return msgParams;
    }
}
