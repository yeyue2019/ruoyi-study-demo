package yeyue.ruoyi.study.module.system.impl.entity.permission;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;
import yeyue.ruoyi.study.module.system.api.enums.permission.RoleTypeEnum;

import static yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants.SYSTEM_ROLE;

/**
 * 角色
 *
 * @author yeyue
 * @date 2022-04-18 23:38:17
 */
@Data
@TableName(value = SYSTEM_ROLE, autoResultMap = true)
public class SystemRoleEntity extends MyBatisEntity {

    /**
     * 角色Id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色标识
     */
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private String code;

    /**
     * 角色排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 角色类型 {@link RoleTypeEnum}
     */
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Integer type;

    /**
     * 备注
     */
    private String remark;
}
