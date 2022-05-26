package yeyue.ruoyi.study.module.system.impl.framework.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.security.core.service.SecurityTokenService;
import yeyue.ruoyi.study.framework.security.core.userdetails.LoginUser;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2TokenService;

import javax.annotation.Resource;

/**
 * @author yeyue
 * @date 2022-05-26 10:34:39
 */
@Slf4j
@Component("st")
public class SystemTokenServiceImpl implements SecurityTokenService {

    @Resource
    SystemOAuth2TokenService tokenService;

    @Override
    public LoginUser validation(String token) {
        SystemOAuth2AccessTokenDomain domain = tokenService.get(token);
        return new LoginUser()
                .setId(domain.getUserId())
                .setUserType(domain.getUserType())
                .setScopes(domain.getScopes());
    }
}
