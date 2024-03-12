package com.jiawa.train.member.controller;

import com.jiawa.train.common.resp.CommonResp;
import com.jiawa.train.member.req.MemberRegisterReq;
import com.jiawa.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
