package com.jiawa.train.member.controller;


import com.jiawa.train.member.req.PassengerSaveReq;
import com.jiawa.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passenger")
@Slf4j
public class PassengerController {


    @Resource
    private PassengerService service;
    @PostMapping("/save")
    public void save(@Valid @RequestBody PassengerSaveReq req){

        Long memberId = req.getMemberId();

        log.info("member_id {}",memberId);
        service.save(req);
    }

}
