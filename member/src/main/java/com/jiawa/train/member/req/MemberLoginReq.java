package com.jiawa.train.member.req;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginReq {

    private String code;

    @NotBlank(message = "手机号为空")
    private String mobile;


}
