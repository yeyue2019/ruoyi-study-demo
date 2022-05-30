package yeyue.ruoyi.study.module.system.api.service.captcha;

import yeyue.ruoyi.study.module.system.api.service.captcha.dto.CaptchaImageRespDTO;

/**
 * @author yeyue
 * @date 2022-05-29 23:03:21
 */
public interface SystemCaptchaService {

    /**
     * 获得验证码图片
     *
     * @return 验证码图片
     */
    CaptchaImageRespDTO getCaptchaImage();

    /**
     * 获得 uuid 对应的验证码
     *
     * @param uuid 验证码编号
     * @return 验证码
     */
    String getCaptchaCode(String uuid);

    /**
     * 删除 uuid 对应的验证码
     *
     * @param uuid 验证码编号
     */
    void deleteCaptchaCode(String uuid);
}
