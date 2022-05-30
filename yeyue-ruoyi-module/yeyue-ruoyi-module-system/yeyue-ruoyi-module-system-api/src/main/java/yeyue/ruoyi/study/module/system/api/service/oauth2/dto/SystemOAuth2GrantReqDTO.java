package yeyue.ruoyi.study.module.system.api.service.oauth2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;
import yeyue.ruoyi.study.module.system.api.enums.oauth2.OAuth2GrantTypeEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-30 13:55:30
 */
@Data
@ApiModel(description = "授权请求")
public class SystemOAuth2GrantReqDTO implements Serializable {

    @ApiModelProperty(value = "客户端Id")
    @NotEmpty(message = "客户端Id不能为空")
    private String clientId;

    @ApiModelProperty(value = "客户端密钥")
    @NotEmpty(message = "客户端密钥不能为空")
    private String clientSecret;

    @InEnum(value = OAuth2GrantTypeEnum.class, message = "授权类型错误")
    @NotNull(message = "授权类型不能为空")
    private String grantType;

    @ApiModelProperty(value = "授权范围")
    private List<String> scopes;

    @ApiModelProperty(value = "授权码")
    @NotNull(groups = AUTHORIZATION_CODE_GROUP.class, message = "授权码不能为空")
    private String code;

    @NotNull(groups = PASSWORD_GROUP.class, message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotNull(groups = PASSWORD_GROUP.class, message = "用户密码不能为空")
    @ApiModelProperty(value = "用户密码")
    private String password;

    @NotNull(groups = REFRESH_TOKEN_GROUP.class, message = "刷新令牌不能为空")
    @ApiModelProperty(value = "刷新令牌")
    private String refreshToken;

    public interface AUTHORIZATION_CODE_GROUP {
    }

    public interface PASSWORD_GROUP {
    }

    public interface REFRESH_TOKEN_GROUP {
    }
}
