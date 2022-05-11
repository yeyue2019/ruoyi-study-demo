package yeyue.ruoyi.study.framework.security.config;

import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import yeyue.ruoyi.study.framework.security.core.authentication.YeyueUserDetailsAuthenticationProvider;
import yeyue.ruoyi.study.framework.security.core.authorize.AuthorizeRequestsCustomizer;
import yeyue.ruoyi.study.framework.security.core.context.TransmittableThreadLocalSecurityContextHolderStrategy;
import yeyue.ruoyi.study.framework.security.core.handler.*;
import yeyue.ruoyi.study.framework.security.core.service.SecurityAuthService;

/**
 * Spring Security 自动配置类，主要用于相关组件的配置
 *
 * @author yeyue
 * @date 2022-04-19 13:07:11
 */
@Configuration
public class YeyueSecurityAutoConfiguration {

    /**
     * Spring Security 加密器
     * 考虑到安全性，这里采用 BCryptPasswordEncoder 加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证失败的处理器
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    /**
     * 权限不足的处理器
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    /**
     * 身份验证的 Provider Bean，通过它实现账号 + 密码的认证
     */
    @Bean
    public YeyueUserDetailsAuthenticationProvider authenticationProvider(SecurityAuthService securityAuthService, PasswordEncoder passwordEncoder) {
        return new YeyueUserDetailsAuthenticationProvider(securityAuthService, passwordEncoder);
    }

    /**
     * 声明调用 {@link SecurityContextHolder#setStrategyName(String)} 方法，
     * 设置使用 {@link TransmittableThreadLocalSecurityContextHolderStrategy} 作为 Security 的上下文策略
     */
    @Bean
    public MethodInvokingFactoryBean securityContextHolderMethodInvokingFactoryBean() {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
        methodInvokingFactoryBean.setTargetMethod("setStrategyName");
        methodInvokingFactoryBean.setArguments(TransmittableThreadLocalSecurityContextHolderStrategy.class.getName());
        return methodInvokingFactoryBean;
    }

    @Bean("frameworkAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer frameworkAuthorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {
            @Override
            public void customize(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
                registry
                        // 静态资源，可匿名访问
                        .antMatchers(HttpMethod.GET, "/*.html", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                        // 健康检查放行
                        .antMatchers("/actuator/**", "/admin-actuator/**").permitAll()
                        // Swagger放行
                        .antMatchers(HttpMethod.GET, "/v3/api-docs", "/swagger-resources").permitAll()
                        // druid放行
                        .antMatchers("/druid/**").permitAll()
                        // 系统测试
                        .antMatchers("/ruoyi/test/security/login").permitAll();
            }
        };
    }
}
