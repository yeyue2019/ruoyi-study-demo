package yeyue.ruoyi.study.module.system.api.service.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author yeyue
 * @date 2022-05-30 13:19:39
 */
@Data
@ApiModel(description = "用户登录请求")
public class SystemAuthLoginByUsernameReqDTO implements Serializable {

    @ApiModelProperty(value = "验证码", required = true, example = "1024", notes = "验证码开启时，需要传递")
    @NotEmpty(message = "验证码不能为空")
    private String code;

    @ApiModelProperty(value = "验证码的唯一标识", required = true, example = "9b2ffbc1-7425-4155-9894-9d5c08541d62", notes = "验证码开启时，需要传递")
    @NotEmpty(message = "唯一标识不能为空")
    private String uuid;

    @ApiModelProperty(value = "账号", required = true, example = "yudaoyuanma")
    @NotEmpty(message = "登录账号不能为空")
    @Length(min = 4, max = 16, message = "账号长度为 4-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;

    @ApiModelProperty(value = "密码", required = true, example = "buzhidao")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    @ApiModelProperty(value = "客户端Id")
    @NotEmpty(message = "客户端Id不能为空")
    private String clientId;
}
