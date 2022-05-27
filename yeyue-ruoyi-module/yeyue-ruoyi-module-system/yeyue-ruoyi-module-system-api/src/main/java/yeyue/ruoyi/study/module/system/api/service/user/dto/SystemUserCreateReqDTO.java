package yeyue.ruoyi.study.module.system.api.service.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;
import yeyue.ruoyi.study.module.system.api.enums.user.GenderEnum;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
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

    @ApiModelProperty(value = "用户昵称", required = true, example = "夜月")
    @Size(max = 30, message = "用户昵称长度不能超过30个字符")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别", example = "F", allowableValues = "F,M,O")
    @NotNull(message = "性别不能为空")
    @InEnum(value = GenderEnum.class, message = "性别不在枚举范围里")
    private String gender;

    @ApiModelProperty(value = "生日", example = "2001-04-04")
    @Past(message = "生日时间错误")
    private LocalDate birthDay;

    @ApiModelProperty(value = "电话区号", example = "86")
    private String areaCode;

    @ApiModelProperty(value = "电话号码")
    @NotNull(message = "手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "邮件")
    @Email(message = "邮件格式不正确")
    private String email;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "部门ID", example = "我是一个用户")
    private Long deptId;

    @ApiModelProperty(value = "岗位编号数组", example = "1")
    private Set<Long> postIds;
}
