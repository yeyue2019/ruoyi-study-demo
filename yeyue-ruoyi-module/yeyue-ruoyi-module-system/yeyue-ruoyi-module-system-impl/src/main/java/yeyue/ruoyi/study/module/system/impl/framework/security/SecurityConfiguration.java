package yeyue.ruoyi.study.module.system.impl.framework.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import yeyue.ruoyi.study.framework.security.core.authorize.AuthorizeRequestsCustomizer;

/**
 * @author yeyue
 * @date 2022-04-28 17:23:12
 */
@Configuration("systemSecurityConfiguration")
public class SecurityConfiguration {

    @Bean("systemAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer systemAuthorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {
            @Override
            public void customize(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
                registry
                        .antMatchers("/web/sys/**")
                        .permitAll();
            }
        };
    }
}
