package yeyue.ruoyi.study.module.system.api.domain.dept;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yeyue
 * @date 2022-05-17 21:22:09
 */
@Data
@ApiModel(description = "系统部门")
public class SystemDeptDomain implements Serializable {

    @ApiModelProperty(value = "部门编号")
    private Long id;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "父部门ID")
    private Long parentId;

    @ApiModelProperty(value = "显示顺序")
    private Integer sort;

    @ApiModelProperty(value = "负责人")
    private Long leaderUserId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "子部门")
    private List<SystemDeptDomain> children;

}
