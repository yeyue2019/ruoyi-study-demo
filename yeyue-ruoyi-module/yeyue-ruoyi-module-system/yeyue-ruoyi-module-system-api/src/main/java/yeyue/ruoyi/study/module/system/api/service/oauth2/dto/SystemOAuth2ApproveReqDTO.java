package yeyue.ruoyi.study.module.system.api.service.oauth2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;
import yeyue.ruoyi.study.module.system.api.enums.oauth2.OAuth2GrantTypeEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-30 14:47:42
 */
@Data
@ApiModel(description = "授权请求")
public class SystemOAuth2ApproveReqDTO implements Serializable {

    @ApiModelProperty(value = "客户端Id")
    @NotEmpty(message = "客户端Id不能为空")
    private String clientId;

    @InEnum(value = OAuth2GrantTypeEnum.class, message = "授权类型错误")
    @NotNull(message = "授权类型不能为空")
    private String grantType;

    @ApiModelProperty(value = "授权范围")
    @NotEmpty(message = "授权范围不能为空")
    private List<String> scopes;

    @ApiModelProperty(value = "重定向地址")
    @NotEmpty(message = "重定向地址不能为空", groups = AUTHORIZATION_CODE_GROUP.class)
    @URL(message = "重定向的URL不正确", groups = AUTHORIZATION_CODE_GROUP.class)
    private String redirectUri;

    @ApiModelProperty(value = "状态值")
    private String state;

    @ApiModelProperty(value = "是否自动授权")
    private Boolean autoApprove;

    public interface AUTHORIZATION_CODE_GROUP {
    }

}
