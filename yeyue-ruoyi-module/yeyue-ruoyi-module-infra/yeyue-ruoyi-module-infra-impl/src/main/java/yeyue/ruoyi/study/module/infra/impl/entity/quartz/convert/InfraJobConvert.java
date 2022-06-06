package yeyue.ruoyi.study.module.infra.impl.entity.quartz.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yeyue.ruoyi.study.module.infra.api.domain.quartz.InfraJobDomain;
import yeyue.ruoyi.study.module.infra.impl.entity.quartz.InfraJobEntity;

/**
 * @author yeyue
 * @date 2022-06-02 14:26:51
 */
@Mapper
public interface InfraJobConvert {

    InfraJobConvert INSTANCE = Mappers.getMapper(InfraJobConvert.class);

    InfraJobDomain toDomain(InfraJobEntity entity);
}
