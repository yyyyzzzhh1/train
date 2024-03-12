package com.jiawa.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.jiawa.train.common.exception.BusinessException;
import com.jiawa.train.common.exception.BusinessExceptionEnum;
import com.jiawa.train.common.resp.CommonResp;
import com.jiawa.train.common.util.SnowUtil;
import com.jiawa.train.member.domain.Member;
import com.jiawa.train.member.domain.MemberExample;
import com.jiawa.train.member.mapper.MemberMapper;
import com.jiawa.train.member.req.MemberRegisterReq;
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
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(req.getMobile());
        List<Member> list = memberMapper.selectByExample(memberExample);

        if(CollUtil.isNotEmpty(list)){
            throw new BusinessException(BusinessExceptionEnum.BUSINESS_EXCEPTION_ENUM);
        }
        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(req.getMobile());
        memberMapper.insert(member);

        return member.getId();
    }
}
