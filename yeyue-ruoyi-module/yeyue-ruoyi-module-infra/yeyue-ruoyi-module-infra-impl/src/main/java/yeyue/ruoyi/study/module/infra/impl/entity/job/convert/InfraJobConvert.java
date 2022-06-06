package yeyue.ruoyi.study.module.infra.impl.entity.job.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yeyue.ruoyi.study.module.infra.api.domain.job.InfraJobDomain;
import yeyue.ruoyi.study.module.infra.api.service.job.dto.InfraJobCreateReqDTO;
import yeyue.ruoyi.study.module.infra.api.service.job.dto.InfraJobStatusUpdateReqDTO;
import yeyue.ruoyi.study.module.infra.api.service.job.dto.InfraJobUpdateReqDTO;
import yeyue.ruoyi.study.module.infra.impl.entity.job.InfraJobEntity;

/**
 * @author yeyue
 * @date 2022-06-02 14:26:51
 */
@Mapper
public interface InfraJobConvert {

    InfraJobConvert INSTANCE = Mappers.getMapper(InfraJobConvert.class);

    InfraJobDomain toDomain(InfraJobEntity entity);

    InfraJobEntity toEntity(InfraJobCreateReqDTO reqDTO);

    InfraJobEntity toEntity(InfraJobUpdateReqDTO reqDTO);

    InfraJobEntity toEntity(InfraJobStatusUpdateReqDTO reqDTO);
}
