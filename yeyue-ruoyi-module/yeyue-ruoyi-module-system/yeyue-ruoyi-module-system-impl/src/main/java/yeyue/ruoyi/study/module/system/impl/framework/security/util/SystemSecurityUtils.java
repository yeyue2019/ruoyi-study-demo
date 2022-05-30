package yeyue.ruoyi.study.module.system.impl.framework.security.util;

import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.security.core.userdetails.LoginUser;
import yeyue.ruoyi.study.framework.security.core.util.SecurityUtils;

import java.util.Set;

/**
 * @author yeyue
 * @date 2022-05-29 22:23:13
 */
public abstract class SystemSecurityUtils {

    public static LoginUser getLoginUser() {
        LoginUser user = SecurityUtils.getLoginUser();
        if (user == null) {
            throw new ServiceException(GlobalErrorCode.UNAUTHORIZED);
        }
        return user;
    }

    public static Long getUserId() {
        return Long.valueOf(getLoginUser().getId());
    }

    public static Integer getUserType() {
        return getLoginUser().getUserType();
    }

    public static Set<String> getUserScopes() {
        return CollectionUtils.toSet(getLoginUser().getScopes());
    }
}
