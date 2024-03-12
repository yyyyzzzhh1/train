package com.jiawa.train.common.exception;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
public enum BusinessExceptionEnum {

    MEMBER_MObILE_EXIST("手机号已存在"),
    MEMBER_MOBILE_NOT_EXIST("手机号不存在，请发送验证码"),
    MEMBER_CODE_ERROR("验证码错误");


    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
