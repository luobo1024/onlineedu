package com.luobo.order.service;

import com.luobo.order.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author luobo
 * @since 2021-07-14
 */
public interface PayLogService extends IService<PayLog> {

    Map createNative(String orderNo);

    Map queryPayStatus(String orderNo);

    void updateOrdersStatus(Map<String, String> map);
}
