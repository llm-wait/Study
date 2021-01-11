package com.startest.wm.config.redis;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-25 14:31
 * @描述 Redis配置类
 **/
@Configuration
@EnableCaching
public class RedisConfig {
    /*@Bean
    public RedisTemplate<String, Object> initRedisTemplate(RedisConnectionFactory factory) {
        //RedisTemplate默认使用JdkSerializationRedisSerializer对对象进行序列化和反序列化，这样序列化后是一串二进制字符串
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        //初始化Jackson2JsonRedisSerializer序列化对象
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //获取字符串序列化器对象
        RedisSerializer stringRedisSerializer=redisTemplate.getStringSerializer();
        //设定String结构的数据的key使用stringRedisSerializer序列化方式，这样键就能以字符串方式保存
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //设定String结构的数据的value使用jackson2JsonRedisSerializer序列化方式
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        //RedisTemplate首先会自动从RedisConnectionFactory工厂中获取连接，然后执行对应的Redis命令，在最后还会关闭Redis的连接。因此不用再手动设置Redis的连接和闭合
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }*/
}
