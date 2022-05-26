package yeyue.ruoyi.study.module.system.impl.framework.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.security.core.service.SecurityTokenService;
import yeyue.ruoyi.study.framework.security.core.userdetails.LoginUser;

/**
 * @author yeyue
 * @date 2022-05-26 10:34:39
 */
@Slf4j
@Component("st")
public class SystemTokenServiceImpl implements SecurityTokenService {
    @Override
    public LoginUser validation(String token) {
        return null;
    }
}
