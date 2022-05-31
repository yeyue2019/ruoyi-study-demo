package yeyue.ruoyi.study.module.system.impl.controller.captcha;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.module.system.api.service.captcha.SystemCaptchaService;
import yeyue.ruoyi.study.module.system.api.service.captcha.dto.CaptchaImageRespDTO;

import javax.annotation.Resource;

/**
 * @author yeyue
 * @date 2022-05-30 16:56:42
 */
@Api(tags = "验证码")
@RestController
@RequestMapping("/web/sys/captcha")
public class SystemCaptchaController {

    @Resource
    SystemCaptchaService captchaService;

    @GetMapping("/get-image")
    @ApiOperation("生成图片验证码")
    public CommonResult<CaptchaImageRespDTO> getCaptchaImage() {
        return CommonResult.success(captchaService.getCaptchaImage());
    }
}
