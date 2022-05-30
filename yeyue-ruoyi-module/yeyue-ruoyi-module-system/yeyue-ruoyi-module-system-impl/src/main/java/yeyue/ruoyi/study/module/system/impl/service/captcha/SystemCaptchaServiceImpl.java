package yeyue.ruoyi.study.module.system.impl.service.captcha;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.captcha.core.CaptchaImage;
import yeyue.ruoyi.study.framework.captcha.service.CaptchaService;
import yeyue.ruoyi.study.module.system.api.service.captcha.SystemCaptchaService;
import yeyue.ruoyi.study.module.system.api.service.captcha.dto.CaptchaImageRespDTO;

import javax.annotation.Resource;

/**
 * @author yeyue
 * @date 2022-05-30 11:25:48
 */
@Slf4j
@Component
public class SystemCaptchaServiceImpl implements SystemCaptchaService {

    @Resource
    CaptchaService service;

    @Override
    public CaptchaImageRespDTO getCaptchaImage() {
        CaptchaImage result = service.getCaptchaImage();
        return new CaptchaImageRespDTO()
                .setUuid(result.getUuid())
                .setImage(result.getImage());
    }

    @Override
    public String getCaptchaCode(String uuid) {
        return service.getCaptchaCode(uuid);
    }

    @Override
    public void deleteCaptchaCode(String uuid) {
        service.deleteCaptchaCode(uuid);
    }
}
