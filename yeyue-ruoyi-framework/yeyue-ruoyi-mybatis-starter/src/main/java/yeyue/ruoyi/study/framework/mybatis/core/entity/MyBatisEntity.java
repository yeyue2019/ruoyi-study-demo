package yeyue.ruoyi.study.framework.mybatis.core.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;

import lombok.Data;

/**
 * 通用数据库实体
 *
 * @author yeyue
 * @date 2022-04-09 22:02:54
 */
@Data
public abstract class MyBatisEntity implements Serializable {

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    protected LocalDateTime createTime;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    protected String creator;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected String updater;

    /**
     * 逻辑删除标识位
     */
    @TableLogic(value = "false", delval = "true")
    protected Boolean deleted;
}
