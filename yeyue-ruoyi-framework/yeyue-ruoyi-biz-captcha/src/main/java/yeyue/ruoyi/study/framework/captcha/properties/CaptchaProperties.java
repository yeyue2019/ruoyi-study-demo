package yeyue.ruoyi.study.framework.captcha.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author yeyue
 * @date 2022-05-30 01:25:49
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "captcha")
public class CaptchaProperties {

    /**
     * 图片宽
     */
    private int width;

    /**
     * 图片高
     */
    private int height;

    /**
     * 字符个数
     */
    private int codeCount;

    /**
     * 干扰元素个数
     */
    private int interfereCount;

    /**
     * 验证码过期时间
     */
    private Duration timeout;
}
