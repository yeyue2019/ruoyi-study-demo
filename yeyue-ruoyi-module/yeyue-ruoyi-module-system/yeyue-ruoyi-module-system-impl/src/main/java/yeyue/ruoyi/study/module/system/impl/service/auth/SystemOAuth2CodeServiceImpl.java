package yeyue.ruoyi.study.module.system.impl.service.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.util.ids.IdUtils;
import yeyue.ruoyi.study.module.system.api.domain.auth.*;
import yeyue.ruoyi.study.module.system.api.service.auth.*;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.*;
import yeyue.ruoyi.study.module.system.impl.entity.auth.SystemOAuth2CodeEntity;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.mapper.auth.SystemOAuth2CodeMapper;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author yeyue
 * @date 2022-05-17 13:04:53
 */
@Slf4j
@Component
public class SystemOAuth2CodeServiceImpl implements SystemOAuth2CodeService {

    @Resource
    SystemOAuth2CodeMapper codeMapper;
    @Resource
    SystemOAuth2ClientService clientService;
    @Resource
    SystemOAuth2TokenService tokenService;

    @Override
    public String create(SystemOAuth2CodeCreateReqDTO reqDTO) {
        // 校验客户端是否存在
        SystemOAuth2ClientDomain client = clientService.getByClientId(reqDTO.getClientId());
        String code = IdUtils.random(16, true);
        SystemOAuth2CodeEntity codeCompare = codeMapper.selectOne(SystemOAuth2CodeEntity::getCode, code);
        if (codeCompare != null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CODE_EXIST);
        }
        SystemOAuth2CodeEntity entity = new SystemOAuth2CodeEntity();
        entity.setClientId(reqDTO.getClientId());
        entity.setUserId(reqDTO.getUserId());
        entity.setCode(code);
        if (client.getCodeValiditySeconds() != null && client.getCodeValiditySeconds() > 0) {
            entity.setExpiresTime(LocalDateTime.now().plusSeconds(client.getCodeValiditySeconds()));
        }
        entity.setStatus(CommonStatusEnum.ENABLE);
        codeMapper.insert(entity);
        return code;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SystemOAuth2AccessTokenDomain authorize(String code) {
        // 校验code是否存在或是否使用
        SystemOAuth2CodeEntity entity = codeMapper.selectOne(SystemOAuth2CodeEntity::getCode, code);
        if (entity == null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CODE_NOT_EXISTS);
        }
        // 校验code是否被使用过
        if (entity.getStatus() == CommonStatusEnum.DISABLE) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CODE_DISABLE);
        }
        // 校验code是否处于有效期
        LocalDateTime now = LocalDateTime.now();
        if (entity.getExpiresTime() != null && entity.getExpiresTime().isBefore(now)) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CODE_EXPIRES);
        }
        SystemOAuth2AccessTokenDomain accessToken = tokenService.create(new SystemOAuth2AccessTokenCreateReqDTO().setClientId(entity.getClientId()).setUserId(entity.getUserId()));
        entity.setAccessToken(accessToken.getAccessToken());
        entity.setRefreshToken(accessToken.getRefreshToken());
        entity.setStatus(CommonStatusEnum.DISABLE);
        codeMapper.updateById(entity);
        return accessToken;
    }
}
