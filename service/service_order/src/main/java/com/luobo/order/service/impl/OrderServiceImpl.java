package com.luobo.order.service.impl;

import com.luobo.commonutils.ordervo.CourseWebVoOrder;
import com.luobo.commonutils.ordervo.UcenterMemberOrder;
import com.luobo.order.client.EduClient;
import com.luobo.order.client.UcenterClient;
import com.luobo.order.entity.Order;
import com.luobo.order.mapper.OrderMapper;
import com.luobo.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.UUID;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author luobo
 * @since 2021-07-14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;
    @Override
    public String createOrders(String courseId, String memberId) {
        //通过远程调用获取用户信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);
        //通过远程调用获取课程信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        Order order = new Order();
        order.setOrderNo(courseId + System.currentTimeMillis());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
