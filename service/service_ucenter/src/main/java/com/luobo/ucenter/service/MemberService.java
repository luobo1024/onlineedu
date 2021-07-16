package com.luobo.ucenter.service;

import com.luobo.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luobo.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author luobo
 * @since 2021-07-03
 */
public interface MemberService extends IService<Member> {

    String login(Member member);

    void register(RegisterVo registerVo);
}
