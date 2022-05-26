package yeyue.ruoyi.study.module.system.api.service.oauth2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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

    @ApiModelProperty(value = "用户类型")
    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    @ApiModelProperty(value = "授权码过期秒数")
    private Integer codeValiditySeconds;

    @ApiModelProperty(value = "授权范围")
    private List<String> scopes;

    @ApiModelProperty(value = "重定向地址")
    private String redirectUri;

    @ApiModelProperty(value = "状态")
    private String state;
}
