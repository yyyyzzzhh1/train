package com.jiawa.train.member.controller;


import com.jiawa.train.common.context.LoginMemberContext;
import com.jiawa.train.common.req.PageReq;
import com.jiawa.train.common.resp.CommonResp;
import com.jiawa.train.common.resp.PageResp;
import com.jiawa.train.member.req.PassengerQueryReq;
import com.jiawa.train.member.req.PassengerSaveReq;
import com.jiawa.train.member.resp.PassengerQueryResp;
import com.jiawa.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
@Slf4j
public class PassengerController {


    @Resource
    private PassengerService passengerService;
    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody PassengerSaveReq req){

        Long memberId = req.getMemberId();

        log.info("member_id {}",memberId);
        passengerService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query_list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(PassengerQueryReq req){
        req.setMemberId(LoginMemberContext.getId());
        log.info("memberId {}",LoginMemberContext.getId());
        PageResp<PassengerQueryResp> pageResp = passengerService.query(req);

        return new CommonResp<>(pageResp);
    }

    @DeleteMapping("/delete/{id}")

    public CommonResp delete(@PathVariable Long id){
        passengerService.delete(id);

        return new CommonResp();
    }


}
