package com.jiawa.train.member.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerSaveReq {

    private Long id;

    @NotBlank(message = "不能为空")
    private Long memberId;

    @NotBlank(message = "不能为空")
    private String name;

    @NotBlank(message = "不能为空")
    private String idCard;

    @NotBlank(message = "不能为空")
    private String type;

    private Date createTime;

    private Date updateTime;
}
