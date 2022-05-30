package yeyue.ruoyi.study.module.system.impl.framework.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.security.core.service.SecurityPermissionService;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemPermissionService;
import yeyue.ruoyi.study.module.system.impl.framework.security.util.SystemSecurityUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Set;

/**
 * @author yeyue
 * @date 2022-05-26 10:33:50
 */
@Slf4j
@Component("ss")
public class SystemSecurityPermissionServiceImpl implements SecurityPermissionService {

    @Resource
    SystemPermissionService permissionService;

    @Override
    public boolean hasPermission(String permission) {
        return permissionService.hasPermissions(SystemSecurityUtils.getUserId(), false, permission);
    }

    @Override
    public boolean hasAnyPermissions(String... permissions) {
        return permissionService.hasPermissions(SystemSecurityUtils.getUserId(), false, permissions);
    }

    @Override
    public boolean hasAllPermissions(String... permissions) {
        return permissionService.hasPermissions(SystemSecurityUtils.getUserId(), true, permissions);
    }

    @Override
    public boolean hasScope(String scope) {
        Set<String> userScopes = SystemSecurityUtils.getUserScopes();
        if (CollectionUtils.isEmpty(userScopes)) {
            return false;
        }
        return userScopes.contains(scope);
    }

    @Override
    public boolean hasAnyScopes(String... scopes) {
        Set<String> userScopes = SystemSecurityUtils.getUserScopes();
        if (CollectionUtils.isEmpty(userScopes)) {
            return false;
        }
        return Arrays.stream(scopes).anyMatch(userScopes::contains);
    }

    @Override
    public boolean hasAllScopes(String... scopes) {
        Set<String> userScopes = SystemSecurityUtils.getUserScopes();
        if (CollectionUtils.isEmpty(userScopes)) {
            return false;
        }
        return Arrays.stream(scopes).allMatch(userScopes::contains);
    }
}
