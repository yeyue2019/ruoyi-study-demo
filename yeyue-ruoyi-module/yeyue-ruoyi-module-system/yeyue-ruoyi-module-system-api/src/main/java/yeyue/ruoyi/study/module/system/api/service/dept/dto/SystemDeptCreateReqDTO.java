package yeyue.ruoyi.study.module.system.api.service.dept.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author yeyue
 * @date 2022-05-17 21:26:56
 */
@Data
@ApiModel(description = "部门创建")
public class SystemDeptCreateReqDTO implements Serializable {

    @ApiModelProperty(value = "部门名称", required = true, example = "芋道")
    @NotBlank(message = "部门名称不能为空")
    @Size(max = 30, message = "部门名称长度不能超过30个字符")
    private String name;

    @ApiModelProperty(value = "父菜单 ID", example = "0", notes = "顶级部门指定父部门为0")
    @NotNull(message = "父部门Id不能为空")
    private Long parentId;

    @ApiModelProperty(value = "显示顺序不能为空", required = true, example = "1")
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    @ApiModelProperty(value = "负责人的用户编号", example = "1")
    private Long leaderUserId;

    @ApiModelProperty(value = "备注", example = "我是一个角色")
    private String remark;
}
