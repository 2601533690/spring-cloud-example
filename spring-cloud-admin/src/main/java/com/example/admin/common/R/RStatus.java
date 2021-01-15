package com.example.admin.common.R;

public enum RStatus {
    // system code
    OK(0),
    SYSTEM_ERROR(10000),
    INVALID_REQUEST_PARAMETERS(10001),
    IP_NOT_IN_WHITELIST(10002),

    ;

    private int code;

    RStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
