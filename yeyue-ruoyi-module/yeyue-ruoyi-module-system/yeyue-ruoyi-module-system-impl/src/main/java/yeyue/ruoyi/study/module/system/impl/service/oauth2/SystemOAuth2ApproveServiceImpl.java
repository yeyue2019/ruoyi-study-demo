package yeyue.ruoyi.study.module.system.impl.service.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2ApproveService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ApproveCheckReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ApproveGetReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ApproveUpdateReqDTO;
import yeyue.ruoyi.study.module.system.impl.entity.oauth2.SystemOAuth2ApproveEntity;
import yeyue.ruoyi.study.module.system.impl.mapper.oauth2.SystemOAuth2ApproveMapper;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yeyue
 * @date 2022-05-26 16:28:46
 */
@Slf4j
@Component
public class SystemOAuth2ApproveServiceImpl implements SystemOAuth2ApproveService {

    @Resource
    SystemOAuth2ApproveMapper mapper;

    @Override
    public Set<String> get(SystemOAuth2ApproveGetReqDTO reqDTO) {
        Set<String> result = getDatabaseApproveScopes(reqDTO);
        result.addAll(reqDTO.getAutoApproveScopes());
        return result;
    }

    @Override
    public boolean check(SystemOAuth2ApproveCheckReqDTO reqDTO) {
        if (CollectionUtils.containsAll(reqDTO.getAutoApproveScopes(), reqDTO.getScopes())) {
            return true;
        }
        return get(reqDTO).containsAll(reqDTO.getScopes());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SystemOAuth2ApproveUpdateReqDTO reqDTO) {
        // 1.删除历史授权信息
        mapper.deleteByByUserIdAndUserTypeAndClientId(reqDTO.getUserId(), reqDTO.getUserType(), reqDTO.getClientId());
        if (CollectionUtils.isNotEmpty(reqDTO.getScopes())) {
            // 更新授权信息
            List<SystemOAuth2ApproveEntity> entities = reqDTO.getScopes().stream().map(scope -> {
                SystemOAuth2ApproveEntity entity = new SystemOAuth2ApproveEntity();
                entity.setClientId(reqDTO.getClientId());
                entity.setScope(scope);
                entity.setUserId(reqDTO.getUserId());
                entity.setUserType(reqDTO.getUserType());
                if (reqDTO.getApproveValiditySeconds() != null && reqDTO.getApproveValiditySeconds() > 0) {
                    entity.setExpiresTime(LocalDateTime.now().plusSeconds(reqDTO.getApproveValiditySeconds()));
                }
                entity.setDeleted(false);
                return entity;
            }).collect(Collectors.toList());
            mapper.insertBatchSomeColumn(entities);
        }
    }

    private Set<String> getDatabaseApproveScopes(SystemOAuth2ApproveGetReqDTO reqDTO) {
        return mapper.selectListByUserIdAndUserTypeAndClientId(reqDTO.getUserId(), reqDTO.getUserType(), reqDTO.getClientId()).stream().filter(r -> r.getExpiresTime() != null && r.getExpiresTime().isAfter(LocalDateTime.now())).map(SystemOAuth2ApproveEntity::getScope).collect(Collectors.toSet());
    }
}
