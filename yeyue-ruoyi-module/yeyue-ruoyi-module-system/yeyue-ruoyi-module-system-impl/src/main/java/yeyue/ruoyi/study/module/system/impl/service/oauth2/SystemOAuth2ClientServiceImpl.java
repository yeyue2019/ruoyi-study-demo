package yeyue.ruoyi.study.module.system.impl.service.oauth2;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.common.util.enums.EnumUtils;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.framework.redis.core.RedisRepository;
import yeyue.ruoyi.study.framework.redis.domain.RedisDomainDefine;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2ClientDomain;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2ClientService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ClientCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ClientPageReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ClientUpdateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ClientValidateReqDTO;
import yeyue.ruoyi.study.module.system.impl.entity.oauth2.SystemOAuth2ClientEntity;
import yeyue.ruoyi.study.module.system.impl.entity.oauth2.convert.SystemOAuth2ClientConvert;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.mapper.oauth2.SystemOAuth2ClientMapper;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yeyue
 * @date 2022-05-17 09:30:48
 */
@Slf4j
@Component
public class SystemOAuth2ClientServiceImpl implements SystemOAuth2ClientService {
    public static final String REDIS_SYSTEM_OAUTH2_CLIENT_KEY = "system:oauth2:client";
    public static final TypeReference<SystemOAuth2ClientDomain> REDIS_SYSTEM_OAUTH2_CLIENT_TYPE =
            new TypeReference<SystemOAuth2ClientDomain>() {
            };

    @Resource
    SystemOAuth2ClientMapper mapper;
    @Resource
    RedisRepository repository;

    @Override
    public Long create(SystemOAuth2ClientCreateReqDTO reqDTO) {
        // 客户端id不能重复
        if (mapper.selectByClientId(reqDTO.getClientId()) != null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_EXIST);
        }
        SystemOAuth2ClientEntity entity = SystemOAuth2ClientConvert.INSTANCE.toEntity(reqDTO);
        mapper.insert(entity);
        // 清楚缓存
        clearByClientId(entity.getClientId());
        return entity.getId();
    }

    @Override
    public void update(SystemOAuth2ClientUpdateReqDTO reqDTO) {
        // id对应的数据存在
        if (mapper.selectById(reqDTO.getId()) == null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_NOT_EXISTS);
        }
        SystemOAuth2ClientEntity entity = SystemOAuth2ClientConvert.INSTANCE.toEntity(reqDTO);
        mapper.updateById(entity);
        clearByClientId(entity.getClientId());
    }

    @Override
    public void delete(Long id) {
        SystemOAuth2ClientEntity entity = mapper.selectById(id);
        if (entity == null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_NOT_EXISTS);
        }
        mapper.deleteById(id);
        clearByClientId(entity.getClientId());
    }

    @Override
    public SystemOAuth2ClientDomain get(Long id) {
        SystemOAuth2ClientEntity entity = mapper.selectById(id);
        return SystemOAuth2ClientConvert.INSTANCE.toDomain(entity);
    }

    @Override
    public PageResult<SystemOAuth2ClientDomain> list(SystemOAuth2ClientPageReqDTO reqDTO) {
        PageResult<SystemOAuth2ClientEntity> pageResult = mapper.selectPage(reqDTO,
                new MyBatisLambdaQueryWrapper<SystemOAuth2ClientEntity>()
                        .like(SystemOAuth2ClientEntity::getName, reqDTO.getName())
                        .eq(SystemOAuth2ClientEntity::getStatus, reqDTO.getStatus()));
        return CollectionUtils.funcPage(pageResult, SystemOAuth2ClientConvert.INSTANCE::toDomain);
    }

    @Override
    public SystemOAuth2ClientDomain validate(SystemOAuth2ClientValidateReqDTO reqDTO) {
        SystemOAuth2ClientDomain client = getByClientId(reqDTO.getClientId());
        if (client == null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_NOT_EXISTS);
        }
        if (EnumUtils.notEquals(CommonStatusEnum.ENABLE, CommonStatusEnum::getStatus, client.getStatus())) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_STATUS_DISABLE);
        }
        if (StringUtils.isNotBlank(reqDTO.getSecret()) && !StringUtils.equals(reqDTO.getSecret(), client.getSecret())) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_CLIENT_SECRET_ERROR);
        }
        if (reqDTO.getAuthorizedGrantType() != null && !client.getAuthorizedGrantTypes().contains(reqDTO.getAuthorizedGrantType())) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_AUTHORIZED_GRANT_TYPE_NOT_EXISTS);
        }
        if (!CollectionUtils.containsAll(client.getScopes(), reqDTO.getScopes())) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_SCOPE_OVER);
        }
        if (reqDTO.getRedirectUri() != null && !StringUtils.startsWithAny(reqDTO.getRedirectUri(), CollectionUtils.listToArray(client.getRedirectUris(), String.class))) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_REDIRECT_URI_NOT_MATCH);
        }
        return client;
    }

    private SystemOAuth2ClientDomain getByClientId(String clientId) {
        SystemOAuth2ClientDomain domain = repository.get(REDIS_SYSTEM_OAUTH2_CLIENT_KEY, clientId, REDIS_SYSTEM_OAUTH2_CLIENT_TYPE);
        if (domain == null) {
            SystemOAuth2ClientEntity entity = mapper.selectByClientId(clientId);
            if (entity == null) {
                throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_NOT_EXISTS);
            }
            domain = SystemOAuth2ClientConvert.INSTANCE.toDomain(entity);
            repository.save(REDIS_SYSTEM_OAUTH2_CLIENT_KEY,
                    new RedisDomainDefine<>(clientId, domain, 1, TimeUnit.DAYS));
        }
        return domain;
    }

    public void clearByClientId(String clientId) {
        repository.delete(REDIS_SYSTEM_OAUTH2_CLIENT_KEY, clientId);
    }
}
