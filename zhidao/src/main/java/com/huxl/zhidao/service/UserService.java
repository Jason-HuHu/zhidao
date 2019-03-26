package com.huxl.zhidao.service;

import com.huxl.zhidao.dao.LoginTicketDAO;
import com.huxl.zhidao.dao.UserDAO;
import com.huxl.zhidao.model.LoginTicket;
import com.huxl.zhidao.model.User;
import com.huxl.zhidao.util.ZhidaoUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @Autowired
    LoginTicketDAO loginTicketDAO;


    public User selectByName(String name) {
        return userDAO.selectByName(name);
    }
    public User getUser(int userId) {
        return userDAO.selectById(userId);
    }

    public Map<String,Object> login(String username, String password) {
        Map<String,Object> map = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        User user = userDAO.selectByName(username);

        if (user == null) {
            map.put("msg", "用户名不存在");
            return map;
        }

        if (!ZhidaoUtil.MD5(password+user.getSalt()).equals(user.getPassword())) {
            map.put("msg", "密码不正确");
            return map;
        }
        String ticket = addLoginTicket(user.getUserId());
        map.put("ticket", ticket);
        map.put("userId", user.getUserId());
        return map;
    }

    private String addLoginTicket(int userId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000 * 3600 * 24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
        loginTicketDAO.addTicket(ticket);
        return ticket.getTicket();
    }


    public Map<String, Object> register(String username, String password) {
        Map<String,Object> map = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            map.put("msg","用户名不能为空");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("msg","密码不能为空");
            return map;
        }
        User user = userDAO.selectByName(username);
        if (null != user) {
            map.put("msg","此用户名已被注册");
            return map;
        }

        //密码强度
        user = new User();
        user.setUserName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        user.setHeadUrl("");//todo 头像待完善
        user.setPassword(ZhidaoUtil.MD5(password + user.getSalt()));
        userDAO.addUser(user);

        //登录
        String ticket = addLoginTicket(user.getUserId());
        map.put("ticket",ticket);
        return map;
    }

    public void logout(String ticket) {
        loginTicketDAO.updateStatus(ticket,1);
    }
}
