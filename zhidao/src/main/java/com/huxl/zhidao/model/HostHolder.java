package com.huxl.zhidao.model;

import org.springframework.stereotype.Component;

/**
 * @author JasonHu
 * @date 2017-12-13
 * 用户上下文信息，每个线程的用户是不一样的
 */
@Component
public class HostHolder {

    private static ThreadLocal<User> users = new ThreadLocal();

    public User getUser(){
        return users.get();
    }

    public void setUSer(User user){
        users.set(user);
    }

    public void clear(){
        users.remove();
    }
}
