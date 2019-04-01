package com.huxl.zhidao.async;

/**
 * 事件类型
 * @author huxl
 * @since 2019-04-01
 */
public enum EventType {
    /**
     * 点赞
     */
    LIKE(0),
    /**
     * 评论
     */
    COMMENT(1),
    /**
     * 登录
     */
    LOGIN(2),
    /**
     * 邮件
     */
    MAIL(3),
    /**
     * 关注
     */
    FOLLOW(4),
    /**
     * 取消关注
     */
    UNFOLLOW(5),
    /**
     * 添加问题
     */
    ADD_QUESTION(6);

    private int value;

    EventType(int value) { this.value = value; }

    public int getValue() { return value; }
}
