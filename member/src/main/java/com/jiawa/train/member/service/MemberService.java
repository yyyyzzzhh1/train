package com.jiawa.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.jiawa.train.common.exception.BusinessException;
import com.jiawa.train.common.exception.BusinessExceptionEnum;
import com.jiawa.train.common.util.JwtUtil;
import com.jiawa.train.common.util.SnowUtil;
import com.jiawa.train.member.domain.Member;
import com.jiawa.train.member.domain.MemberExample;
import com.jiawa.train.member.mapper.MemberMapper;
import com.jiawa.train.member.req.MemberLoginReq;
import com.jiawa.train.member.req.MemberRegisterReq;
import com.jiawa.train.member.req.MemberSendCodeReq;
import com.jiawa.train.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService  {

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return 0;
    }

    public long register(MemberRegisterReq req){
        Member memberDb = getMembers(req.getMobile());

        if(ObjectUtil.isNotNull(memberDb)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MObILE_EXIST);
        }
        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(req.getMobile());
        memberMapper.insert(member);

        return member.getId();
    }


    public void SendCode(MemberSendCodeReq req){

        Member memberDB= getMembers(req.getMobile());

        if(ObjectUtil.isNull(memberDB)){
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(req.getMobile());
            memberMapper.insert(member);
        }

       RandomUtil.randomString(6);

    }

    public MemberLoginResp login(MemberLoginReq req){
        Member members = getMembers(req.getMobile());
        if(ObjectUtil.isNull(members)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        if(!"8888".equals(req.getCode())){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_CODE_ERROR);
        }

        MemberLoginResp memberLoginResp = BeanUtil.copyProperties(members, MemberLoginResp.class);
        String token = JwtUtil.createToken(memberLoginResp.getId(), memberLoginResp.getMobile());

        memberLoginResp.setToken(token);

        return memberLoginResp;


    }


    private Member getMembers(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
