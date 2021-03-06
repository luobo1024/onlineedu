package com.luobo.order.service;

import com.luobo.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author luobo
 * @since 2021-07-14
 */
public interface OrderService extends IService<Order> {

    String createOrders(String courseId, String memberId);
}
