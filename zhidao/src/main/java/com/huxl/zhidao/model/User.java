package com.huxl.zhidao.model;

/**
 * @author huxingl
 * 用户
 */
public class User {
    private int userId;
    private String userName;
    private String password;
    private String salt;
    private String headUrl;

    public User(){}
    public User(String userName) {
        this.userName = userName;
        this.password = "";
        this.salt = "";
        this.headUrl = "";
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
