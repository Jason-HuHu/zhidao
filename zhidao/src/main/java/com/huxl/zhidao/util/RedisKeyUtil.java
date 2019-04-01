package com.huxl.zhidao.util;

/**
 * 获取Redis key 工具类
 * @author huxl
 * @since 2019-04-01
 */
public class RedisKeyUtil {
    private static final String SPLIT = ":";
    private static final String BIZ_LIKE = "LIKE";
    private static final String BIZ_DISLIKE = "DISLIKE";
    //事件队列
    private static final String BIZ_EVENTQUEUE = "EVENT_QUEUE";
    // 获取粉丝
    private static final String BIZ_FOLLOWER = "FOLLOWER";
    // 关注对象
    private static final String BIZ_FOLLOWEE = "FOLLOWEE";
    //Feed流
    private static final String BIZ_TIMELINE = "TIMELINE";

    /**
     * 记录点赞的key
     * @param entityType 实体类型
     * @param entityId 实体Id
     * @return 返回key
     */
    public static String getLikeKey(int entityType, int entityId) {
        return BIZ_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

    /**
     * 记录点踩的key
     * @param entityType 实体类型
     * @param entityId 实体Id
     * @return 返回key
     */
    public static String getDisLikeKey(int entityType, int entityId) {
        return BIZ_DISLIKE + SPLIT + entityType + SPLIT + entityId;
    }

    public static String getEventQueueKey() {
        return BIZ_EVENTQUEUE;
    }

    /**
     * 某个实体的粉丝key
     * @param entityType 实体类型
     * @param entityId 实体Id
     * @return 返回key：FOLLOWER：type:id
     */
    public static String getFollowerKey(int entityType, int entityId) {
        return BIZ_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }

    /**
     * 每个用户对某类实体的关注key
     * @param userId 用户id
     * @param entityType 实体类型
     * @return 返回key：FOLLOWEE：userId:type
     */
    public static String getFolloweeKey(int userId, int entityType) {
        return BIZ_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    /**
     *
     * @param userId 用户Id
     * @return timeline 的key
     */
    public static String getTimelineKey(int userId) {
        return BIZ_TIMELINE + SPLIT + userId;
    }
}
