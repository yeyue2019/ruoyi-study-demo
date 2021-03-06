package yeyue.ruoyi.study.module.system.api.service.permission.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;
import yeyue.ruoyi.study.module.system.api.enums.permission.MenuTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author yeyue
 * @date 2022-05-23 15:23:15
 */
@Data
@ApiModel(description = "菜单创建")
public class SystemMenuCreateReqDTO implements Serializable {

    @ApiModelProperty(value = "菜单名称", required = true, example = "系统设置")
    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 50, message = "菜单名称长度不能超过50个字符")
    private String name;

    @ApiModelProperty(value = "权限标识", example = "system:menu:create", notes = "仅菜单类型为按钮时，才需要传递")
    @Size(max = 100)
    private String permission;

    @ApiModelProperty(value = "类型", required = true, example = "1", notes = "参见 MenuTypeEnum 枚举类")
    @NotNull(message = "菜单类型不能为空")
    @InEnum(value = MenuTypeEnum.class, message = "菜单类型错误")
    private Integer type;

    @ApiModelProperty(value = "显示顺序不能为空", required = true, example = "1")
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    @ApiModelProperty(value = "父菜单 ID", required = true, example = "0")
    @NotNull(message = "父菜单ID不能为空")
    private Long parentId;

    @ApiModelProperty(value = "路由地址", example = "post", notes = "仅菜单类型为菜单或者目录时，才需要传")
    @Size(max = 200, message = "路由地址不能超过200个字符")
    private String path;

    @ApiModelProperty(value = "菜单图标", example = "/menu/list", notes = "仅菜单类型为菜单或者目录时，才需要传")
    private String icon;

    @ApiModelProperty(value = "组件路径", example = "system/menu/index", notes = "仅菜单类型为菜单时，才需要传")
    @Size(max = 200, message = "组件路径不能超过255个字符")
    private String component;
}
