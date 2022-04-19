package yeyue.ruoyi.study.framework.security.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.security.core.service.SecurityAuthService;
import yeyue.ruoyi.study.framework.security.core.userdetails.WebLoginUser;

import javax.annotation.Resource;

/**
 * @author yeyue
 * @date 2022-04-19 13:48:27
 */
@Slf4j
@Component
public class MockAuthServiceImpl implements SecurityAuthService {

    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.equals(username, "yeyue")) {
            throw new UsernameNotFoundException(username);
        }
        WebLoginUser user = new WebLoginUser();
        user.setUsername("yeyue");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setId(1L);
        user.setStatus(0);
        return user;
    }
}