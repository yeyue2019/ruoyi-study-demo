package yeyue.ruoyi.study.module.system.api.service.auth.dto;

import io.swagger.annotations.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-17 09:49:03
 */
@Data
@ApiModel(description = "更改OAuth2客户端状态")
public class SystemOAuth2ClientStatusUpdateReqDTO implements Serializable {

    @ApiModelProperty(value = "客户端id集合")
    @NotNull(message = "客户端信息不能为空")
    @Size(min = 1, max = 100, message = "修改岗位的数量介于1-100之间")
    private List<Long> ids;

    @ApiModelProperty(value = "状态")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "状态枚举错误")
    private Integer status;
}
