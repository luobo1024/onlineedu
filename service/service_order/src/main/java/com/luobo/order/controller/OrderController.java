package com.luobo.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luobo.commonutils.JwtUtils;
import com.luobo.commonutils.Msg;
import com.luobo.order.entity.Order;
import com.luobo.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author luobo
 * @since 2021-07-14
 */
@RestController
@RequestMapping("/order/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder/{courseId}")
    public Msg saveOrder(@PathVariable String courseId, HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //返回订单
        String orderNo = orderService.createOrders(courseId, memberId);
        return Msg.success().data("orderId",orderNo);
    }
    @GetMapping("getOrderInfo/{orderId}")
    public Msg getOrderInfo(@PathVariable String orderId){
        QueryWrapper<Order> wrapper = new QueryWrapper<Order>();
        wrapper.eq("order_no", orderId);
        Order order = orderService.getOne(wrapper);
        return Msg.success().data("item", order);

    }
    //根据课程Id和用户id查询订单表中的订单状态
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId,@PathVariable String memberId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status", 1);
        int count = orderService.count(wrapper);
        if(count > 0){
            return true;
        }
        return false;
    }
}

