package com.jiawa.train.member.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberSendCodeReq {

    @NotBlank(message = "手机号为空")
    @Pattern(regexp = "^1\\d{10}$", message = "手机号码格式错误")
    private String mobile;
}
