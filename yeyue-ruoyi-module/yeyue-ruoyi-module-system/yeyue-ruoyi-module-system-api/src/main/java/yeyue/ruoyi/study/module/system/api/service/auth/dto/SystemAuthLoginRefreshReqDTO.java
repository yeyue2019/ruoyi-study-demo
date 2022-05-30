package yeyue.ruoyi.study.module.system.api.service.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author yeyue
 * @date 2022-05-30 13:40:10
 */
@Data
@ApiModel(description = "刷新访问令牌")
public class SystemAuthLoginRefreshReqDTO implements Serializable {

    @ApiModelProperty(value = "客户端Id")
    @NotEmpty(message = "客户端Id不能为空")
    private String clientId;

    @ApiModelProperty(value = "刷新令牌")
    @NotEmpty(message = "刷新令牌不能为空")
    private String refreshToken;
}
