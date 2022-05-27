package yeyue.ruoyi.study.module.system.impl.framework.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.security.core.service.SecurityAuthService;
import yeyue.ruoyi.study.framework.security.core.service.SecurityPermissionService;
import yeyue.ruoyi.study.framework.security.core.userdetails.LoginUser;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemPermissionService;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author yeyue
 * @date 2022-05-26 10:33:50
 */
@Slf4j
@Component("ss")
public class SystemSecurityPermissionServiceImpl implements SecurityPermissionService {

    @Resource
    SecurityAuthService authService;
    @Resource
    SystemPermissionService permissionService;

    @Override
    public boolean hasPermission(String permission) {
        LoginUser user = authService.get();
        return permissionService.hasPermissions(Long.valueOf(user.getId()), false, permission);
    }

    @Override
    public boolean hasAnyPermissions(String... permissions) {
        LoginUser user = authService.get();
        return permissionService.hasPermissions(Long.valueOf(user.getId()), false, permissions);
    }

    @Override
    public boolean hasAllPermissions(String... permissions) {
        LoginUser user = authService.get();
        return permissionService.hasPermissions(Long.valueOf(user.getId()), true, permissions);
    }

    @Override
    public boolean hasScope(String scope) {
        LoginUser user = authService.get();
        if (CollectionUtils.isEmpty(user.getScopes())) {
            return false;
        }
        return user.getScopes().contains(scope);
    }

    @Override
    public boolean hasAnyScopes(String... scopes) {
        LoginUser user = authService.get();
        if (CollectionUtils.isEmpty(user.getScopes())) {
            return false;
        }
        return Arrays.stream(scopes).anyMatch(user.getScopes()::contains);
    }

    @Override
    public boolean hasAllScopes(String... scopes) {
        LoginUser user = authService.get();
        if (CollectionUtils.isEmpty(user.getScopes())) {
            return false;
        }
        return Arrays.stream(scopes).allMatch(user.getScopes()::contains);
    }
}
