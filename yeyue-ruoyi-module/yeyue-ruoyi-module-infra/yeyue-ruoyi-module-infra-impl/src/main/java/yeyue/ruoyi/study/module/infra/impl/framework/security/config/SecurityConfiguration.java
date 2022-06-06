package yeyue.ruoyi.study.module.infra.impl.framework.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import yeyue.ruoyi.study.framework.security.core.authorize.AuthorizeRequestsCustomizer;

/**
 * @author yeyue
 * @date 2022-04-28 17:23:12
 */
@Configuration("infraSecurityConfiguration")
public class SecurityConfiguration {

    @Bean("infraAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer systemAuthorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {
            @Override
            public void
            customize(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
                registry.antMatchers("/web/infra/**").permitAll();
            }
        };
    }
}
