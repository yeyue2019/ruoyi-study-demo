package yeyue.ruoyi.study.module.infra.impl.controller.captcha;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.module.infra.api.service.captcha.InfraCaptchaService;
import yeyue.ruoyi.study.module.infra.api.service.captcha.dto.CaptchaImageRespDTO;

import javax.annotation.Resource;

/**
 * @author yeyue
 * @date 2022-05-30 16:56:42
 */
@Api(tags = "验证码")
@RestController
@RequestMapping("/web/infra/captcha")
public class InfraCaptchaController {

    @Resource
    InfraCaptchaService captchaService;

    @GetMapping("/get-image")
    @ApiOperation("生成图片验证码")
    public CommonResult<CaptchaImageRespDTO> getCaptchaImage() {
        return CommonResult.success(captchaService.getCaptchaImage());
    }
}
