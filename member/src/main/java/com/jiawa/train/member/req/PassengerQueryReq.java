package com.jiawa.train.member.req;

import com.jiawa.train.common.req.PageReq;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerQueryReq extends PageReq {

    @NotNull(message = "不能为空")
    private Long memberId;
}
