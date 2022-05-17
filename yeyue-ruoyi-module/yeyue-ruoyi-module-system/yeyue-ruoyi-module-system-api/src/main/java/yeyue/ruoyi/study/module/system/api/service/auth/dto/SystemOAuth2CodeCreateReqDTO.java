package yeyue.ruoyi.study.module.system.api.service.auth.dto;

import io.swagger.annotations.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author yeyue
 * @date 2022-05-17 13:02:39
 */
@Data
@ApiModel(description = "创建授权码")
public class SystemOAuth2CodeCreateReqDTO implements Serializable {

    @NotEmpty(message = "客户端Id不能为空")
    @ApiModelProperty(value = "客户端Id")
    private String clientId;

    @NotEmpty(message = "用户Id不能为空")
    @ApiModelProperty(value = "用户Id")
    private String userId;
}
