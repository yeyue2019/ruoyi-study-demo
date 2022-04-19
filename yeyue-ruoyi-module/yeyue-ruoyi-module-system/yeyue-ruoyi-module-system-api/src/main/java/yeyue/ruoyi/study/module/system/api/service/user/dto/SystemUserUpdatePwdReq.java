package yeyue.ruoyi.study.module.system.api.service.user.dto;

import io.swagger.annotations.*;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @author yeyue
 * @date 2022-04-18 16:05:09
 */
@Data
@ApiModel(description = "用户密码修改请求")
public class SystemUserUpdatePwdReq implements Serializable {

    @ApiModelProperty(value = "旧密码", required = true, example = "123456")
    @NotEmpty(message = "旧密码不能为空")
    @Size(min = 6, max = 16, message = "密码长度为 4-16 位")
    private String oldPassword;

    @ApiModelProperty(value = "新密码", required = true, example = "654321")
    @NotEmpty(message = "新密码不能为空")
    @Size(min = 6, max = 16, message = "密码长度为 4-16 位")
    private String newPassword;
}
