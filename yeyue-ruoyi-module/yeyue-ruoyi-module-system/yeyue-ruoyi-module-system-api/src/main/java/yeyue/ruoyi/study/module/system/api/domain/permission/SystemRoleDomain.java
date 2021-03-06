package yeyue.ruoyi.study.module.system.api.domain.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yeyue
 * @date 2022-05-24 10:36:18
 */
@Data
@ApiModel(description = "系统角色")
public class SystemRoleDomain implements Serializable {

    @ApiModelProperty(value = "角色Id")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色标识")
    private String code;

    @ApiModelProperty(value = "角色排序")
    private Integer sort;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "角色类型")
    private Integer type;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更改时间")
    private LocalDateTime updateTime;
}
