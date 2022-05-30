package yeyue.ruoyi.study.module.system.api.service.captcha.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yeyue
 * @date 2022-05-29 23:04:50
 */
@Data
@ApiModel(description = "图片验证码信息")
public class CaptchaImageRespDTO implements Serializable {

    @ApiModelProperty(value = "uuid", example = "1b3b7d00-83a8-4638-9e37-d67011855968", notes = "验证码的唯一标识")
    private String uuid;

    @ApiModelProperty(value = "图片", notes = "验证码的图片内容，使用 Base64 编码")
    private String image;
}
