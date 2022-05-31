package yeyue.ruoyi.study.module.system.impl.controller.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author yeyue
 * @date 2022-05-29 21:45:20
 */
@Data
@ApiModel(description = "用户信息")
public class SystemUserRespVO implements Serializable {

    @ApiModelProperty(value = "用户Id")
    private Long id;

    @ApiModelProperty(value = "账号", required = true, example = "yeyue")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别", example = "F", allowableValues = "F,M,O")
    private String gender;

    @ApiModelProperty(value = "生日", example = "2001-04-04")
    private LocalDate birthDay;

    @ApiModelProperty(value = "电话区号", example = "86")
    private String areaCode;

    @ApiModelProperty(value = "电话号码")
    private String mobile;

    @ApiModelProperty(value = "邮件")
    private String email;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "用户状态")
    private Integer status;

    @ApiModelProperty(value = "部门Id")
    private Long deptId;

    @ApiModelProperty(value = "岗位编号Id组")
    private Set<Long> postIds;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
