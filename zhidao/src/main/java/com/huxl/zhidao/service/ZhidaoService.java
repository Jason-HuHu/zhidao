package com.huxl.zhidao.service;

import org.springframework.stereotype.Service;


@Service
public class ZhidaoService {
    public String getMessage(int userId) {
        return "Hello Message:" + String.valueOf(userId);
    }
}
