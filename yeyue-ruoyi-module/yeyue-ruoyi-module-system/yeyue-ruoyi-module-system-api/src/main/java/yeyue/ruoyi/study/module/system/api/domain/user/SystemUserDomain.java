package yeyue.ruoyi.study.module.system.api.domain.user;

import io.swagger.annotations.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.GenderEnum;
import yeyue.ruoyi.study.framework.common.validation.annotation.InStringEnum;
import yeyue.ruoyi.study.framework.common.validation.core.ApiCommand;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.*;
import java.util.Set;

/**
 * 管理员用户
 *
 * @author yeyue
 * @date 2022-04-18 13:58:25
 */
@Data
@ApiModel(description = "系统用户")
public class SystemUserDomain implements Serializable {

    @ApiModelProperty(value = "Id")
    @Null(groups = ApiCommand.Create.class, message = "创建时不需要传入Id")
    @NotNull(groups = ApiCommand.Update.class, message = "用户Id不能为空")
    private Long id;

    @ApiModelProperty(value = "账号", required = true, example = "yeyue")
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 4, max = 30, message = "用户账号长度为4-30个字符")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,30}$", message = "用户账号由 数字、字母 组成")
    private String username;

    @ApiModelProperty(value = "密码:只有创建时需要传入")
    @NotEmpty(groups = ApiCommand.Create.class, message = "用户密码不能为空")
    @Size(min = 6, max = 16, groups = ApiCommand.Create.class, message = "密码长度为6-16位")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别", example = "F", allowableValues = "F,M,O")
    @InStringEnum(GenderEnum.class)
    private String gender;

    @ApiModelProperty(value = "生日", example = "2001-04-04")
    private LocalDate birthDay;

    @ApiModelProperty(value = "电话区号", example = "86")
    private String areaCode;

    @ApiModelProperty(value = "电话号码")
    private String mobile;

    @ApiModelProperty(value = "邮件")
    @Email(message = "邮件格式不正确")
    private String email;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "用户状态")
    private Integer status;

    @ApiModelProperty(value = "部门Id")
    private Long deptId;

    @ApiModelProperty(value = "岗位编号Id组")
    private Set<Long> postIds;

    @ApiModelProperty(value = "创建时间", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private LocalDateTime createTime;
}
