package yeyue.ruoyi.study.module.system.impl.entity.permission;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;
import yeyue.ruoyi.study.module.system.api.enums.permission.MenuTypeEnum;

import static yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants.SYSTEM_MENU;

/**
 * 菜单（权限）
 *
 * @author yeyue
 * @date 2022-04-18 23:28:00
 */
@Data
@TableName(value = SYSTEM_MENU, autoResultMap = true)
public class SystemMenuEntity extends MyBatisEntity {

    /**
     * 菜单Id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 权限标识
     * <p>
     * 一般格式为：${系统}:${模块}:${操作}
     * 例如说：system:admin:add，即 system 服务的添加管理员。
     * <p>
     * 当我们把该 SystemMenuEntity 赋予给角色后，意味着该角色有该资源：
     * - 对于后端，配合 @PreAuthorize 注解，配置 API 接口需要该权限，从而对 API 接口进行权限控制。
     * - 对于前端，配合前端标签，配置按钮是否展示，避免用户没有该权限时，结果可以看到该操作。
     */
    private String permission;

    /**
     * 菜单类型 {@link MenuTypeEnum}
     */
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Integer type;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 父菜单ID fix 创建后不可修改
     */
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Long parentId;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 状态
     */
    private Integer status;
}
