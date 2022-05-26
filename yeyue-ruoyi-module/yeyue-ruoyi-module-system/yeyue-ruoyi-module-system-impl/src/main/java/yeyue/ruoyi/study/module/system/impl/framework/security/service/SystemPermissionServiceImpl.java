package yeyue.ruoyi.study.module.system.impl.framework.security.service;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import yeyue.ruoyi.study.framework.security.core.service.SecurityPermissionService;

/**
 * @author yeyue
 * @date 2022-05-26 10:33:50
 */
@Slf4j
@Component("ss")
public class SystemPermissionServiceImpl implements SecurityPermissionService {

    @Override
    public boolean hasPermission(String permission) {
        return false;
    }

    @Override
    public boolean hasAnyPermissions(String... permissions) {
        return false;
    }

    @Override
    public boolean hasAllPermissions(String... permissions) {
        return false;
    }

    @Override
    public boolean hasScope(String scope) {
        return false;
    }

    @Override
    public boolean hasAnyScopes(String... scopes) {
        return false;
    }

    @Override
    public boolean hasAllScopes(String... scopes) {
        return false;
    }
}
