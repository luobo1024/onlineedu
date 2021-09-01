package com.luobo.order.controller;


import com.luobo.commonutils.Msg;
import com.luobo.order.entity.PayLog;
import com.luobo.order.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author luobo
 * @since 2021-07-14
 */
@RestController
@RequestMapping("/order/pay-log")
@CrossOrigin
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    @GetMapping("createNative/{orderNo}")
    public Msg createNative(@PathVariable String orderNo){
        Map map = payLogService.createNative(orderNo);
        System.out.println("返回二维码map"+ map);
        return Msg.success().data(map);
    }
    @GetMapping("queryPayStatus/{orderNo}")
    public Msg queryPayStatus(@PathVariable String orderNo){
        Map map = payLogService.queryPayStatus(orderNo);
        System.out.println("查询订单状态map:" + map);
        if(map == null){
            return Msg.error().message("支付出错了");
        }
        if(map.get("trade_state").equals("SUCCESS")){
            payLogService.updateOrdersStatus(map);
            return Msg.success();
        }
        return Msg.error().message("支付出错了");
    }
}

