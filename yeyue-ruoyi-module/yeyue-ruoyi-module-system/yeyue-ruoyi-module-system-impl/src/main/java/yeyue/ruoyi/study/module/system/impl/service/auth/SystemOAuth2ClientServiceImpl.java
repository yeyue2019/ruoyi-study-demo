package yeyue.ruoyi.study.module.system.impl.service.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.module.system.api.domain.auth.SystemOAuth2ClientDomain;
import yeyue.ruoyi.study.module.system.api.service.auth.SystemOAuth2ClientService;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.*;
import yeyue.ruoyi.study.module.system.impl.entity.auth.SystemOAuth2ClientEntity;
import yeyue.ruoyi.study.module.system.impl.entity.auth.convert.SystemOAuth2ClientConvert;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.mapper.auth.SystemOAuth2ClientMapper;

import javax.annotation.Resource;

/**
 * @author yeyue
 * @date 2022-05-17 09:30:48
 */
@Slf4j
@Component
public class SystemOAuth2ClientServiceImpl implements SystemOAuth2ClientService {

    @Resource
    SystemOAuth2ClientMapper clientMapper;

    @Override
    public Long create(SystemOAuth2ClientCreateReqDTO reqDTO) {
        // 客户端id不能重复
        if (clientMapper.selectOne(SystemOAuth2ClientEntity::getClientId, reqDTO.getClientId()) != null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_EXIST);
        }
        SystemOAuth2ClientEntity entity = SystemOAuth2ClientConvert.INSTANCE.toEntity(reqDTO);
        entity.setStatus(CommonStatusEnum.ENABLE);
        clientMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void update(SystemOAuth2ClientUpdateReqDTO reqDTO) {
        // id对应的数据存在
        if (clientMapper.selectById(reqDTO.getId()) == null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_NOT_EXISTS);
        }
        // clientId 不能重复
        SystemOAuth2ClientEntity clientIdCompare = clientMapper.selectOne(SystemOAuth2ClientEntity::getClientId, reqDTO.getClientId());
        if (clientIdCompare != null && clientIdCompare.getId().compareTo(reqDTO.getId()) != 0) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_EXIST);
        }
        SystemOAuth2ClientEntity entity = SystemOAuth2ClientConvert.INSTANCE.toEntity(reqDTO);
        clientMapper.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStatus(SystemOAuth2ClientStatusUpdateReqDTO reqDTO) {
        return clientMapper.updateBatchColumnByIds(SystemOAuth2ClientConvert.INSTANCE.toEntity(reqDTO), reqDTO.getIds());
    }

    @Override
    public void delete(Long id) {
        SystemOAuth2ClientEntity entity = clientMapper.selectById(id);
        if (entity == null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_NOT_EXISTS);
        }
        clientMapper.deleteById(id);
    }

    @Override
    public SystemOAuth2ClientDomain get(Long id) {
        SystemOAuth2ClientEntity entity = clientMapper.selectById(id);
        return SystemOAuth2ClientConvert.INSTANCE.toDomain(entity);
    }

    @Override
    public PageResult<SystemOAuth2ClientDomain> list(SystemOAuth2ClientPageReqDTO reqDTO) {
        PageResult<SystemOAuth2ClientEntity> pageResult = clientMapper.selectPage(reqDTO, new MyBatisLambdaQueryWrapper<SystemOAuth2ClientEntity>()
                .like(SystemOAuth2ClientEntity::getName, reqDTO.getName())
                .eq(SystemOAuth2ClientEntity::getStatus, reqDTO.getStatus()));
        return CollectionUtils.convertPage(pageResult, SystemOAuth2ClientConvert.INSTANCE::toDomain);
    }

    @Override
    public SystemOAuth2ClientDomain getByClientId(String clientId) {
        SystemOAuth2ClientEntity entity = clientMapper.selectOne(SystemOAuth2ClientEntity::getClientId, clientId);
        if (entity == null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_NOT_EXISTS);
        }
        if (entity.getStatus() == CommonStatusEnum.DISABLE) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CLIENT_STATUS_DISABLE);
        }
        return SystemOAuth2ClientConvert.INSTANCE.toDomain(entity);
    }
}
