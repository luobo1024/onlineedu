package com.luobo.order.client;


import com.luobo.commonutils.ordervo.CourseWebVoOrder;
import com.luobo.order.service.impl.OrderServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-edu", fallback = OrderServiceImpl.class)
public interface EduClient {
    @GetMapping("/eduservice/coursefront/getCourseInfo/{courseId}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("courseId") String courseId);
}
