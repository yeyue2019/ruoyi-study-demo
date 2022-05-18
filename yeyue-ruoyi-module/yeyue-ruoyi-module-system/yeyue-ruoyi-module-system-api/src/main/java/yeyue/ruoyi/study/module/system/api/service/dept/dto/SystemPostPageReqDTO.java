package yeyue.ruoyi.study.module.system.api.service.dept.dto;

import io.swagger.annotations.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageParam;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

/**
 * @author yeyue
 * @date 2022-05-11 23:56:58
 */
@Data
@ApiModel(description = "岗位查询条件")
public class SystemPostPageReqDTO extends PageParam {

    @ApiModelProperty(value = "岗位编码", example = "yudao", notes = "模糊匹配")
    private String code;

    @ApiModelProperty(value = "岗位名称", example = "芋道", notes = "模糊匹配")
    private String name;

    @ApiModelProperty(value = "展示状态", example = "1", notes = "参见 CommonStatusEnum 枚举类")
    @InEnum(CommonStatusEnum.class)
    private Integer status;
}
