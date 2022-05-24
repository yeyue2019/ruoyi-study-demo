package yeyue.ruoyi.study.module.system.impl.entity.auth.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yeyue.ruoyi.study.module.system.api.domain.auth.SystemOAuth2ClientDomain;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemOAuth2ClientCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemOAuth2ClientUpdateReqDTO;
import yeyue.ruoyi.study.module.system.impl.entity.auth.SystemOAuth2ClientEntity;

/**
 * @author yeyue
 * @date 2022-05-17 09:37:16
 */
@Mapper
public interface SystemOAuth2ClientConvert {
    SystemOAuth2ClientConvert INSTANCE = Mappers.getMapper(SystemOAuth2ClientConvert.class);

    SystemOAuth2ClientEntity toEntity(SystemOAuth2ClientCreateReqDTO reqDTO);

    SystemOAuth2ClientEntity toEntity(SystemOAuth2ClientUpdateReqDTO reqDTO);

    SystemOAuth2ClientDomain toDomain(SystemOAuth2ClientEntity entity);
}
