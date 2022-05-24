package yeyue.ruoyi.study.module.system.api.service.permission.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;
import yeyue.ruoyi.study.module.system.api.enums.permission.DataScopeEnum;
import yeyue.ruoyi.study.module.system.api.enums.permission.RoleTypeEnum;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author yeyue
 * @date 2022-05-24 10:43:14
 */
@Data
@ApiModel(description = "创建角色")
public class SystemRoleCreateReqDTO implements Serializable {

    @ApiModelProperty(value = "角色名称", required = true, example = "管理员")
    @NotBlank(message = "角色名称不能为空")
    @Size(max = 30, message = "角色名称长度不能超过30个字符")
    private String name;

    @NotBlank(message = "角色标志不能为空")
    @Size(max = 100, message = "角色标志长度不能超过100个字符")
    @ApiModelProperty(value = "角色编码", required = true, example = "ADMIN")
    private String code;

    @ApiModelProperty(value = "显示顺序不能为空", required = true, example = "1024")
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    @ApiModelProperty(value = "备注", example = "我是一个角色")
    private String remark;

    @ApiModelProperty(value = "角色类型")
    @InEnum(value = RoleTypeEnum.class, message = "角色类型错误")
    @NotNull(message = "角色类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "数据范围", required = true, example = "1", notes = "参见 DataScopeEnum 枚举类")
    @NotNull(message = "数据范围不可为空")
    @InEnum(value = DataScopeEnum.class, message = "数据范围不在可选范围内")
    private Integer dataScope;

    @ApiModelProperty(value = "数据范围(指定部门数组)", example = "1")
    private Set<Long> dataScopeDeptIds;
}
