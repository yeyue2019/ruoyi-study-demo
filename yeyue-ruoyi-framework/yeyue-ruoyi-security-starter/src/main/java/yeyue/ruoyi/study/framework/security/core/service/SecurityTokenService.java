package yeyue.ruoyi.study.framework.security.core.service;

import yeyue.ruoyi.study.framework.security.core.userdetails.LoginUser;

/**
 * @author yeyue
 * @date 2022-05-26 10:24:59
 */
public interface SecurityTokenService {

    /**
     * 根据token获取用户
     *
     * @param token 用户的Token
     * @return 结果
     */
    LoginUser validation(String token);
}
