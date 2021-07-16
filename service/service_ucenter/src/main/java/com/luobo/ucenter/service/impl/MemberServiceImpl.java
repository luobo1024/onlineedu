package com.luobo.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luobo.commonutils.JwtUtils;
import com.luobo.commonutils.MD5;
import com.luobo.servicebase.exceptionhandler.EduException;
import com.luobo.ucenter.entity.Member;
import com.luobo.ucenter.entity.vo.RegisterVo;
import com.luobo.ucenter.mapper.MemberMapper;
import com.luobo.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author luobo
 * @since 2021-07-03
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate<String, String > redisTemplate;
    @Override
    public String login(Member member) {
        String mobile = member.getMobile();
        String password = member.getPassword();
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new EduException(20001,"登录失败");
        }
        QueryWrapper<Member> wrapper = new QueryWrapper();
        wrapper.eq("mobile",mobile);
        Member mobileMember = baseMapper.selectOne(wrapper);
        if(mobileMember == null){
            throw  new EduException(20001,"登录失败");
        }

        if(!MD5.encrypt(password).equals(mobileMember.getPassword())){
            throw  new EduException(20001,"登录失败");
        }
        if(mobileMember.getIsDeleted()){
            throw  new EduException(20001,"登录失败");
        }
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;
    }

    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        if(StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)){
            throw  new EduException(20001,"注册不成功");
        }
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(redisCode)){
            throw  new EduException(20001,"验证码错误");
        }
        String MD5pswd = MD5.encrypt(password);
        Member member = new Member();
        member.setMobile(mobile);
        member.setPassword(MD5pswd);
        member.setNickname(nickname);
        member.setIsDeleted(false);
        member.setAvatar("https://onlineedu1010.oss-cn-beijing.aliyuncs.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        baseMapper.insert(member);

    }
}
