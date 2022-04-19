package yeyue.ruoyi.study.module.system.impl.entity.permission;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;

import static yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants.SYSTEM_USER_ROLE;

/**
 * 用户角色关联表
 *
 * @author yeyue
 * @date 2022-04-19 09:05:58
 */
@Data
@TableName(SYSTEM_USER_ROLE)
public class SystemUserRoleEntity extends MyBatisEntity {

    /**
     * 系统Id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 角色Id
     */
    private Long roleId;
}
