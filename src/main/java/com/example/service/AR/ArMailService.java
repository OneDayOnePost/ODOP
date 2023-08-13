package com.example.service.AR;

import org.springframework.stereotype.Service;

@Service
public interface ArMailService {
    String sendSimpleMessage(String email)throws Exception;
}