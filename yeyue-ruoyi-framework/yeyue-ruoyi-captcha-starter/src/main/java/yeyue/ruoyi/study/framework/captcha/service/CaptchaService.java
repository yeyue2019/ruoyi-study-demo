package yeyue.ruoyi.study.framework.captcha.service;

import yeyue.ruoyi.study.framework.captcha.core.CaptchaImage;

/**
 * @author yeyue
 * @date 2022-05-30 02:40:34
 */
public interface CaptchaService {

    /**
     * 获得验证码图片
     *
     * @return 验证码图片
     */
    CaptchaImage getCaptchaImage();

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
