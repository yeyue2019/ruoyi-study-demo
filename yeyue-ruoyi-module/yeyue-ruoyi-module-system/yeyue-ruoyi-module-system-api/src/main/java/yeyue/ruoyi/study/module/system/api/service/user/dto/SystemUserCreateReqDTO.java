package yeyue.ruoyi.study.module.system.api.service.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author yeyue
 * @date 2022-05-27 11:19:31
 */
@Data
@ApiModel(description = "创建用户")
public class SystemUserCreateReqDTO implements Serializable {

    @ApiModelProperty(value = "用户账号", required = true, example = "yeyue")
    @NotBlank(message = "用户账号不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,30}$", message = "用户账号由 数字、字母 组成")
    @Size(min = 4, max = 30, message = "用户账号长度为 4-30 个字符")
    private String username;

    @ApiModelProperty(value = "密码", required = true, example = "123456")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "部门ID", example = "1")
    @NotNull(message = "部门不能为空")
    private Long deptId;

    @ApiModelProperty(value = "岗位编号数组", example = "1")
    @NotNull(message = "岗位不能为空")
    private Set<Long> postIds;
}
