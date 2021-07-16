package com.luobo.eduservice.client;


import com.luobo.eduservice.client.impl.OrderClientImpl;
import com.luobo.eduservice.controller.front.CourseFrontController;
import com.luobo.eduservice.service.impl.EduCourseServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-order", fallback = OrderClientImpl.class)
public interface OrdersClient {
    @GetMapping("/order/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
