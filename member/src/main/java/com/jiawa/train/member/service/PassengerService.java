package com.jiawa.train.member.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.train.common.context.LoginMemberContext;
import com.jiawa.train.common.resp.PageResp;
import com.jiawa.train.common.util.SnowUtil;
import com.jiawa.train.member.domain.Passenger;
import com.jiawa.train.member.domain.PassengerExample;
import com.jiawa.train.member.mapper.PassengerMapper;
import com.jiawa.train.member.req.PassengerQueryReq;
import com.jiawa.train.member.req.PassengerSaveReq;
import com.jiawa.train.member.resp.PassengerQueryResp;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PassengerService {

    @Resource
    private PassengerMapper passengerMapper;
    public void save(PassengerSaveReq req){

        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        if(ObjectUtil.isNull(passenger.getId())){
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setCreateTime(new Date());
            passenger.setUpdateTime(new Date());
            passengerMapper.insert(passenger);
        }else{
            passenger.setUpdateTime(new Date());
            passengerMapper.updateByPrimaryKey(passenger);
        }

    }


    public static void main(String[] args) {

    }
    public PageResp<PassengerQueryResp> query(PassengerQueryReq req){


        PassengerExample passengerExample = new PassengerExample();
        if(ObjectUtil.isNotNull(req)){
            passengerExample.createCriteria().andMemberIdEqualTo(req.getMemberId());
        }
        List<Passenger> passengers = passengerMapper.selectByExample(passengerExample);
        PageHelper.startPage(req.getPage(),req.getSize());
        PageInfo<Passenger> pageInfo = new PageInfo<>(passengers);

        List<PassengerQueryResp> passengerQueryResp = BeanUtil.copyToList(passengers, PassengerQueryResp.class);
        PageResp pageResp = new PageResp();
        pageResp.setList(passengerQueryResp);
        pageResp.setTotal(pageInfo.getTotal());
        return pageResp;
    }


    public void delete(Long id) {
        passengerMapper.deleteByPrimaryKey(id);
    }
}
