package yeyue.ruoyi.study.module.infra.impl.entity.job;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;

import static yeyue.ruoyi.study.module.infra.impl.constants.InfraTableConstants.INFRA_JOB;


/**
 * @author yeyue
 * @date 2022-06-02 13:08:13
 */
@Data
@TableName(value = INFRA_JOB, autoResultMap = true)
public class InfraJobEntity extends MyBatisEntity {

    /**
     * 任务编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务状态
     * <p>
     * 枚举 {@link yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum}
     */
    private Integer status;

    /**
     * 处理器的名字
     */
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private String handlerName;

    /**
     * 处理器的参数
     */
    private String handlerParam;

    /**
     * CRON 表达式
     */
    private String cronExpression;

    /**
     * 重试次数
     * 如果不重试，则设置为 0
     */
    private Integer retryCount;

    /**
     * 重试间隔，单位：毫秒
     * 如果没有间隔，则设置为 0
     */
    private Integer retryInterval;
}
