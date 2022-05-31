package yeyue.ruoyi.study.module.system.api.service.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yeyue
 * @date 2022-05-27 17:40:32
 */
@Data
@ApiModel(description = "获取用户信息")
public class SystemUserGetReqDTO implements Serializable {

    @ApiModelProperty(value = "用户Id")
    private Long id;

    @ApiModelProperty(value = "用户账号")
    private String username;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "邮件")
    private String email;

    @ApiModelProperty(value = "用户状态")
    private Integer status;
}
