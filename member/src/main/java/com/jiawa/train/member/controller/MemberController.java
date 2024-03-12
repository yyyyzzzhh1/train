package com.jiawa.train.member.controller;

import com.jiawa.train.common.resp.CommonResp;
import com.jiawa.train.member.req.MemberLoginReq;
import com.jiawa.train.member.req.MemberRegisterReq;
import com.jiawa.train.member.req.MemberSendCodeReq;
import com.jiawa.train.member.resp.MemberLoginResp;
import com.jiawa.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {



    @Resource

    private MemberService memberService;

    @GetMapping("/count")
    public Integer count(){
        return memberService.count();
    }


    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req){
        CommonResp<Long> longCommonResp = new CommonResp<>();
        longCommonResp.setContent(memberService.register(req));
        return longCommonResp;
    }


    @PostMapping("/sendCode")
    public CommonResp<String> sendCode(@Valid @RequestBody MemberSendCodeReq req){
        CommonResp<String> longCommonResp = new CommonResp<>();
        memberService.SendCode(req);
        return longCommonResp;

    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq req){

        CommonResp commonResp = new CommonResp();
        MemberLoginResp memberLoginResp = memberService.login(req);
        commonResp.setContent(memberLoginResp);
        return commonResp;
    }

}
