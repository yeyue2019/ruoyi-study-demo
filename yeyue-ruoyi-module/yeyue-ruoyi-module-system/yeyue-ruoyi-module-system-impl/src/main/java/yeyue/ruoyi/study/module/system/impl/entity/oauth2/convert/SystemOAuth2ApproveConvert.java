package yeyue.ruoyi.study.module.system.impl.entity.oauth2.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2ApproveDomain;
import yeyue.ruoyi.study.module.system.impl.entity.oauth2.SystemOAuth2ApproveEntity;

/**
 * @author yeyue
 * @date 2022-05-26 16:23:44
 */
@Mapper
public interface SystemOAuth2ApproveConvert {
    SystemOAuth2ApproveConvert INSTANCE = Mappers.getMapper(SystemOAuth2ApproveConvert.class);

    SystemOAuth2ApproveDomain toDomain(SystemOAuth2ApproveEntity entity);

}
