package yeyue.ruoyi.study.module.system.impl.entity.auth.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yeyue.ruoyi.study.module.system.api.domain.auth.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.impl.entity.auth.SystemOAuth2AccessTokenEntity;

/**
 * @author yeyue
 * @date 2022-05-17 16:30:47
 */
@Mapper
public interface SystemOAuth2AccessTokenConvert {
    SystemOAuth2AccessTokenConvert INSTANCE = Mappers.getMapper(SystemOAuth2AccessTokenConvert.class);

    SystemOAuth2AccessTokenDomain toDomain(SystemOAuth2AccessTokenEntity entity);

}
