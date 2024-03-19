package com.jiawa.train.member.service;


import cn.hutool.core.bean.BeanUtil;
import com.jiawa.train.common.util.SnowUtil;
import com.jiawa.train.member.domain.Passenger;
import com.jiawa.train.member.mapper.PassengerMapper;
import com.jiawa.train.member.req.PassengerSaveReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PassengerService {

    @Resource
    private PassengerMapper passengerMapper;
    public void save(PassengerSaveReq req){

        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(new Date());
        passenger.setUpdateTime(new Date());

        passengerMapper.insert(passenger);

    }
}
