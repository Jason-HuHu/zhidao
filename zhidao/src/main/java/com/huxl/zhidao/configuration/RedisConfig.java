package com.huxl.zhidao.configuration;

import com.huxl.zhidao.controller.FeedController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *  redis配置类
 *
 * @author huxl
 * @since 2019-04-01
 */
@Configuration
public class RedisConfig {

    private static final Logger logger = LoggerFactory.getLogger(FeedController.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMills;

//    @Value("${spring.redis.password}")
//    private String password;

    @Bean
    public JedisPool redisPoolFactory() throws Exception{
        logger.info("init redis ===> address:" + host + ":" + port );
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMills);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout);
        return jedisPool;
    }
}
