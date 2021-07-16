package com.luobo.msm.service;

import org.springframework.stereotype.Service;

@Service
public interface MsmService {
    boolean sendMail(String email, String code);
}
