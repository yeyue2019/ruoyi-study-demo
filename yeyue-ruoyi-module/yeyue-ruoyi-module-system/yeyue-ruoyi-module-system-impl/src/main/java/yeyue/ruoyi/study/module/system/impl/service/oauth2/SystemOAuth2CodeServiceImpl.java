package yeyue.ruoyi.study.module.system.impl.service.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.util.ids.IdUtils;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2CodeDomain;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2CodeService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2CodeCreateReqDTO;
import yeyue.ruoyi.study.module.system.impl.entity.oauth2.SystemOAuth2CodeEntity;
import yeyue.ruoyi.study.module.system.impl.entity.oauth2.convert.SystemOAuth2CodeConvert;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.mapper.oauth2.SystemOAuth2CodeMapper;

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
    SystemOAuth2CodeMapper mapper;

    @Override
    public String create(SystemOAuth2CodeCreateReqDTO reqDTO) {
        // 校验客户端是否存在
        String code = IdUtils.random(32, true);
        SystemOAuth2CodeEntity codeCompare = mapper.selectByCode(code);
        if (codeCompare != null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CODE_EXIST);
        }
        SystemOAuth2CodeEntity entity = new SystemOAuth2CodeEntity();
        entity.setClientId(reqDTO.getClientId());
        entity.setUserId(reqDTO.getUserId());
        entity.setUserType(reqDTO.getUserType());
        entity.setScopes(reqDTO.getScopes());
        entity.setRedirectUri(reqDTO.getRedirectUri());
        entity.setState(reqDTO.getState());
        entity.setCode(code);
        if (reqDTO.getCodeValiditySeconds() != null && reqDTO.getCodeValiditySeconds() > 0) {
            entity.setExpiresTime(LocalDateTime.now().plusSeconds(reqDTO.getCodeValiditySeconds()));
        }
        mapper.insert(entity);
        return code;
    }

    @Override
    public SystemOAuth2CodeDomain consume(String code) {
        // 校验code是否存在或是否使用
        SystemOAuth2CodeEntity entity = mapper.selectByCode(code);
        if (entity == null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CODE_NOT_EXISTS);
        }
        // 校验code是否处于有效期
        LocalDateTime now = LocalDateTime.now();
        if (entity.getExpiresTime() != null && entity.getExpiresTime().isBefore(now)) {
            throw new ServiceException(SystemErrorCode.OAUTH2_CODE_EXPIRES);
        }
        mapper.deleteById(entity);
        return SystemOAuth2CodeConvert.INSTANCE.toDomain(entity);
    }
}
