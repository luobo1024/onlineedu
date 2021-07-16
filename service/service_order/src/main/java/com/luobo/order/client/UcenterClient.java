package com.luobo.order.client;


import com.luobo.commonutils.ordervo.UcenterMemberOrder;
import com.luobo.order.service.impl.OrderServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-ucenter", fallback = OrderServiceImpl.class)
public interface UcenterClient {
    @GetMapping("/ucenter/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
