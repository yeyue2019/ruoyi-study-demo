package yeyue.ruoyi.study.module.system.api.service.dept.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageParam;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

/**
 * @author yeyue
 * @date 2022-05-11 23:56:58
 */
@Data
@ApiModel(description = "岗位查询")
public class SystemPostPageReqDTO extends PageParam {

    @ApiModelProperty(value = "岗位编码", example = "ceo", notes = "模糊匹配")
    private String code;

    @ApiModelProperty(value = "岗位名称", example = "总经理", notes = "模糊匹配")
    private String name;

    @ApiModelProperty(value = "展示状态", example = "1", notes = "参见 CommonStatusEnum 枚举类")
    @InEnum(CommonStatusEnum.class)
    private Integer status;
}
