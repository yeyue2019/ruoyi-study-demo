package yeyue.ruoyi.study.module.system.api.service.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageParam;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

/**
 * 用户列表查询
 *
 * @author yeyue
 * @date 2022-04-18 16:10:55
 */
@Data
@ApiModel(description = "用户分页查询")
public class SystemUserPageReqDTO extends PageParam {

    @ApiModelProperty(value = "用户账号", example = "yeyue", notes = "模糊匹配")
    private String username;

    @ApiModelProperty(value = "手机号码", example = "150xx", notes = "模糊匹配")
    private String mobile;

    @ApiModelProperty(value = "展示状态", example = "1", notes = "参见 CommonStatusEnum 枚举类")
    @InEnum(value = CommonStatusEnum.class)
    private Integer status;

    @ApiModelProperty(value = "部门编号", example = "1024")
    private Long deptId;
}
