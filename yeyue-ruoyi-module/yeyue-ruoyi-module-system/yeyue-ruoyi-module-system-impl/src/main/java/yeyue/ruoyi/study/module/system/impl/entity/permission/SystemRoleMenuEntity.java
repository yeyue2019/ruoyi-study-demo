package yeyue.ruoyi.study.module.system.impl.entity.permission;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;

import static yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants.SYSTEM_ROLE_MENU;

/**
 * 角色权限关联表
 *
 * @author yeyue
 * @date 2022-04-19 09:03:36
 */
@Data
@TableName(SYSTEM_ROLE_MENU)
public class SystemRoleMenuEntity extends MyBatisEntity {

    /**
     * 系统Id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色Id
     */
    private Long roleId;

    /**
     * 菜单Id
     */
    private Long menuId;
}
