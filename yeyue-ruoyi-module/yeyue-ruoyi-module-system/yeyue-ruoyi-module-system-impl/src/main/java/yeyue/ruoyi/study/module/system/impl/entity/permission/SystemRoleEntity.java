package yeyue.ruoyi.study.module.system.impl.entity.permission;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.mybatis.core.type.JsonLongSetTypeHandler;
import yeyue.ruoyi.study.module.system.api.enums.*;
import yeyue.ruoyi.study.module.system.impl.entity.SystemEntity;

import java.util.Set;

import static yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants.SYSTEM_ROLE;

/**
 * 角色
 *
 * @author yeyue
 * @date 2022-04-18 23:38:17
 */
@Data
@TableName(value = SYSTEM_ROLE, autoResultMap = true)
public class SystemRoleEntity extends SystemEntity {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色标识
     */
    private RoleCodeEnum code;

    /**
     * 角色排序
     */
    private Integer sort;

    /**
     * 角色类型
     */
    private RoleTypeEnum type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数据范围
     */
    private DataScopeEnum dataScope;

    /**
     * 数据范围(指定部门数组)
     */
    @TableField(typeHandler = JsonLongSetTypeHandler.class)
    private Set<Long> dataScopeDeptIds;
}
