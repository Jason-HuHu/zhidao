package com.huxl.zhidao.service;

import com.huxl.zhidao.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huxingl
 * @Date 2017/11/6
 * 用户Service
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    UserDAO userDAO;

    public Map<String,String> login(String username, String password) {
        Map<String,String> map = new HashMap<>();
        return map;
    }



}
