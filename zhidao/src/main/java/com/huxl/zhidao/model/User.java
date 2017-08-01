package com.huxl.zhidao.model;

/**
 * Created by nowcoder on 2016/7/10.
 */
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public String getDescription() {
        return "This is " + name;
    }
}
