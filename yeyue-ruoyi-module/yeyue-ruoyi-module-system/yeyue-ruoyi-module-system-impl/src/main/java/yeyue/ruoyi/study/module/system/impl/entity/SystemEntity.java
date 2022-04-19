package yeyue.ruoyi.study.module.system.impl.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;

/**
 * 系统公共实体类
 *
 * @author yeyue
 * @date 2022-04-18 23:53:45
 */
@Data
public abstract class SystemEntity extends MyBatisEntity {

    /**
     * 系统Id
     */
    @TableId(type = IdType.ASSIGN_ID)
    protected Long id;

    /**
     * 状态
     */
    @TableField(updateStrategy = FieldStrategy.NEVER)
    protected CommonStatusEnum status;
}
