package yeyue.ruoyi.study.framework.security.core.authentication;

import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import yeyue.ruoyi.study.framework.security.core.service.SecurityAuthService;

/**
 * 自定义用户身份认证
 *
 * @author yeyue
 * @date 2022-04-19 13:58:34
 */
public class YeyueUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final SecurityAuthService service;

    private final PasswordEncoder passwordEncoder;

    public YeyueUserDetailsAuthenticationProvider(SecurityAuthService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }


    // copy 自 DaoAuthenticationProvider 的 additionalAuthenticationChecks 方法

    /**
     * 用户鉴权
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // 校验 credentials
        if (authentication.getCredentials() == null) {
            this.logger.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        // 校验 password
        String presentedPassword = authentication.getCredentials().toString();
        if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            this.logger.debug("Failed to authenticate since password does not match stored value");
            throw new BadCredentialsException(this.messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
    }

    /**
     * 用户获取
     */
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // 判断Token属于自定义的类型否则不予处理
        if (!(authentication instanceof YeyueUsernamePasswordAuthenticationToken)) {
            throw new PreAuthenticatedCredentialsNotFoundException(authentication.getClass().getSimpleName());
        }
        // 获取自定义的参数进行处理
        YeyueUsernamePasswordAuthenticationToken token = (YeyueUsernamePasswordAuthenticationToken) authentication;
        // 获取并加载用户信息
        return service.loadUserByUsername(username);
    }
}
