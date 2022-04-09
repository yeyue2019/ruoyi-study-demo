package yeyue.ruoyi.study.framework.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.context.annotation.*;

/**
 * redis自动配置
 *
 * @author yeyue
 * @date 2022-04-09 18:14:19
 */
@Configuration
public class YeyueRedisAutoConfiguration {

    // TODO: 2022/4/9 因为封装实现的原因 不使用redisTemplate作为redis操作工具 使用Redisson的RedissonClient

    /**
     * 使用系统中存在的ObjectMapper作为JsonJacksonCodec的序列化工具
     */
    @Bean
    public JsonJacksonCodec jsonJacksonCodec(ObjectMapper objectMapper) {
        return new JsonJacksonCodec(objectMapper);
    }
}
