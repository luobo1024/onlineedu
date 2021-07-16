package com.luobo.eduservice.client.impl;

import com.luobo.eduservice.client.OrdersClient;
import org.springframework.stereotype.Component;

@Component
public class OrderClientImpl implements OrdersClient {
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        System.out.println(666);
        return false;
    }
}
