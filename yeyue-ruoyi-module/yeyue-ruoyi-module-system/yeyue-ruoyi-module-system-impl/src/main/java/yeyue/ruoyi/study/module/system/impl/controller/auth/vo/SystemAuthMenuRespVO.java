package yeyue.ruoyi.study.module.system.impl.controller.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-30 16:41:08
 */
@Data
@ApiModel(description = "用户菜单")
public class SystemAuthMenuRespVO implements Serializable {

    @ApiModelProperty(value = "菜单名称", required = true, example = "芋道")
    private Long id;

    @ApiModelProperty(value = "父菜单 ID", required = true, example = "1024")
    private Long parentId;

    @ApiModelProperty(value = "菜单名称", required = true, example = "芋道")
    private String name;

    @ApiModelProperty(value = "路由地址", example = "post", notes = "仅菜单类型为菜单或者目录时，才需要传")
    private String path;

    @ApiModelProperty(value = "组件路径", example = "system/post/index", notes = "仅菜单类型为菜单时，才需要传")
    private String component;

    @ApiModelProperty(value = "菜单图标", example = "/menu/list", notes = "仅菜单类型为菜单或者目录时，才需要传")
    private String icon;

    @ApiModelProperty(value = "显示顺序")
    private Integer sort;

    @ApiModelProperty(value = "子路由")
    private List<SystemAuthMenuRespVO> children;
}
