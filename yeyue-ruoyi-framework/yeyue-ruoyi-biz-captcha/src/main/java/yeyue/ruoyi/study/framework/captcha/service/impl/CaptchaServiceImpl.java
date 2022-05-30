package yeyue.ruoyi.study.framework.captcha.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.captcha.core.CaptchaImage;
import yeyue.ruoyi.study.framework.captcha.properties.CaptchaProperties;
import yeyue.ruoyi.study.framework.captcha.service.CaptchaService;
import yeyue.ruoyi.study.framework.captcha.util.CaptchaUtils;
import yeyue.ruoyi.study.framework.common.util.ids.IdUtils;
import yeyue.ruoyi.study.framework.redis.core.RedisRepository;
import yeyue.ruoyi.study.framework.redis.domain.RedisDomainDefine;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yeyue
 * @date 2022-05-30 02:46:23
 */
@Slf4j
@Component
public class CaptchaServiceImpl implements CaptchaService {
    public static final String CAPTCHA_CACHE_KEY = "captcha:code";

    @Resource
    CaptchaProperties properties;
    @Resource
    RedisRepository repository;

    @Override
    public CaptchaImage getCaptchaImage() {
        String uuid = IdUtils.uuid(true);
        String code = IdUtils.random(properties.getCodeCount(), true);
        String image = CaptchaUtils.getCaptcha(properties.getWidth(), properties.getHeight(), code, properties.getInterfereCount());
        repository.save(CAPTCHA_CACHE_KEY, new RedisDomainDefine<>(uuid, code, properties.getTimeout().getSeconds(), TimeUnit.SECONDS));
        return new CaptchaImage().setUuid(uuid).setImage(image);
    }

    @Override
    public String getCaptchaCode(String uuid) {
        return repository.get(CAPTCHA_CACHE_KEY, uuid, new TypeReference<String>() {
        });
    }

    @Override
    public void deleteCaptchaCode(String uuid) {
        repository.delete(CAPTCHA_CACHE_KEY, uuid);
    }
}
