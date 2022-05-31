package yeyue.ruoyi.study.framework.captcha.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yeyue
 * @date 2022-05-30 02:41:21
 */
@Data
@ApiModel(description = "验证码")
public class CaptchaImage implements Serializable {

    @ApiModelProperty(value = "验证码编号")
    private String uuid;

    @ApiModelProperty(value = "验证码信息")
    private String image;
}
