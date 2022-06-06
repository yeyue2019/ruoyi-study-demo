package yeyue.ruoyi.study.module.infra.impl.mapper.job;

import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.module.infra.impl.entity.job.InfraJobEntity;

/**
 * @author yeyue
 * @date 2022-06-02 15:14:06
 */
public interface InfraJobMapper extends MyBatisMapper<InfraJobEntity> {

    default InfraJobEntity selectByHandlerName(String handlerName) {
        return selectOne(InfraJobEntity::getHandlerName, handlerName);
    }
}
