//package com.gnet.integration.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//@Slf4j
//public class RedisConfig {
//
//    @Value("${redis.host}")
//    private String redisHost;
//
//    @Value("${redis.port}")
//    private int redisPort;
//
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
//        log.info("RedisConfig-jedisConnectionFactory--------------->{}", redisStandaloneConfiguration);
//        return new JedisConnectionFactory(redisStandaloneConfiguration);
//    }
//
//    @Bean
//    public StringRedisSerializer stringRedisSerializer() {
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        return stringRedisSerializer;
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        template.setDefaultSerializer(stringRedisSerializer());
//        template.setKeySerializer(stringRedisSerializer());
//        template.setHashKeySerializer(stringRedisSerializer());
//        template.setHashValueSerializer(stringRedisSerializer());
//        log.info("RedisTemplate-redisTemplate--------------->{}", template);
//        return template;
//    }
//}
