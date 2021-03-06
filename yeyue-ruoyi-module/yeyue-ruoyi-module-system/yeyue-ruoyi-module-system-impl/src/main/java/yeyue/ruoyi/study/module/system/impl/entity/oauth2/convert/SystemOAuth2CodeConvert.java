package yeyue.ruoyi.study.module.system.impl.entity.oauth2.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2CodeDomain;
import yeyue.ruoyi.study.module.system.impl.entity.oauth2.SystemOAuth2CodeEntity;

/**
 * @author yeyue
 * @date 2022-05-17 15:02:28
 */
@Mapper
public interface SystemOAuth2CodeConvert {
    SystemOAuth2CodeConvert INSTANCE = Mappers.getMapper(SystemOAuth2CodeConvert.class);

    SystemOAuth2CodeDomain toDomain(SystemOAuth2CodeEntity entity);
}
