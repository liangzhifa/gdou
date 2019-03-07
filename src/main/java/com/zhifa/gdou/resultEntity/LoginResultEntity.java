package com.zhifa.gdou.resultEntity;

import java.io.Serializable;

public class LoginResultEntity implements Serializable{
    private Integer code;
    private Object data;

    public LoginResultEntity() {
    }

    public LoginResultEntity(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
