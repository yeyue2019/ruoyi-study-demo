package yeyue.ruoyi.study.module.system.impl.service.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2ClientDomain;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2ApproveService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2ClientService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ClientValidateReqDTO;
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
    @Resource
    SystemOAuth2ClientService clientService;

    @Override
    public Set<String> get(String userId, Integer userType, String clientId, SystemOAuth2ClientDomain client) {
        if (client == null) {
            client = clientService.validate(
                    new SystemOAuth2ClientValidateReqDTO()
                            .setClientId(clientId)
            );
        }
        Set<String> result = getDatabaseApproveScopes(userId, userType, clientId);
        result.addAll(client.getAutoApproveScopes());
        return result;
    }

    @Override
    public boolean check(String userId, Integer userType, String clientId, List<String> scopes, SystemOAuth2ClientDomain client) {
        if (client == null) {
            client = clientService.validate(
                    new SystemOAuth2ClientValidateReqDTO()
                            .setClientId(clientId)
                            .setScopes(scopes)
            );
        }
        if (CollectionUtils.containsAll(client.getAutoApproveScopes(), scopes)) {
            return true;
        }
        return get(userId, userType, clientId, client).containsAll(scopes);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(String userId, Integer userType, String clientId, List<String> scopes, SystemOAuth2ClientDomain client) {
        if (client == null) {
            client = clientService.validate(
                    new SystemOAuth2ClientValidateReqDTO()
                            .setClientId(clientId)
                            .setScopes(scopes)
            );
        }
        Integer approveValiditySeconds = client.getApproveValiditySeconds();
        // 1.删除历史授权信息
        mapper.deleteByByUserIdAndUserTypeAndClientId(userId, userType, clientId);
        if (CollectionUtils.isNotEmpty(scopes)) {
            // 更新授权信息
            List<SystemOAuth2ApproveEntity> entities = CollectionUtils.funcList(scopes, scope -> {
                SystemOAuth2ApproveEntity entity = new SystemOAuth2ApproveEntity();
                entity.setClientId(clientId);
                entity.setScope(scope);
                entity.setUserId(userId);
                entity.setUserType(userType);
                if (approveValiditySeconds != null && approveValiditySeconds > 0) {
                    entity.setExpiresTime(LocalDateTime.now().plusSeconds(approveValiditySeconds));
                }
                entity.setDeleted(false);
                return entity;
            });
            mapper.insertBatchSomeColumn(entities);
        }
    }

    private Set<String> getDatabaseApproveScopes(String userId, Integer userType, String clientId) {
        return mapper.selectListByUserIdAndUserTypeAndClientId(userId, userType, clientId)
                .stream()
                .filter(r -> r.getExpiresTime() != null && r.getExpiresTime().isAfter(LocalDateTime.now()))
                .map(SystemOAuth2ApproveEntity::getScope)
                .collect(Collectors.toSet());
    }
}
