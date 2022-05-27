package yeyue.ruoyi.study.framework.security.core.service;

import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.security.core.userdetails.LoginUser;
import yeyue.ruoyi.study.framework.security.core.util.SecurityUtils;

/**
 * @author yeyue
 * @date 2022-05-26 10:24:59
 */
public interface SecurityAuthService {

    /**
     * 根据token获取用户
     *
     * @param token 用户的Token
     * @return 结果
     */
    LoginUser validation(String token);

    /**
     * 获取登录后的用户
     *
     * @return 用户信息
     */
    default LoginUser get() {
        LoginUser user = SecurityUtils.getLoginUser();
        if (user == null) {
            throw new ServiceException(GlobalErrorCode.UNAUTHORIZED);
        }
        return user;
    }
}
