package yeyue.ruoyi.study.module.system.api.service.auth.dto;

import io.swagger.annotations.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageParam;
import yeyue.ruoyi.study.framework.common.validation.annotation.InIntEnum;

/**
 * @author yeyue
 * @date 2022-05-17 10:00:43
 */
@Data
@ApiModel(description = "OAuth2 客户端分页查询")
public class SystemOAuth2ClientPageReqDTO extends PageParam {

    @ApiModelProperty(value = "客户端名称")
    private String name;

    @ApiModelProperty(value = "展示状态", example = "1", notes = "参见 CommonStatusEnum 枚举类")
    @InIntEnum(CommonStatusEnum.class)
    private Integer status;
}
