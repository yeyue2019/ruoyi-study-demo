package yeyue.ruoyi.study.framework.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import yeyue.ruoyi.study.framework.security.core.authentication.YeyueUserDetailsAuthenticationProvider;
import yeyue.ruoyi.study.framework.security.core.filter.JwtAuthenticationTokenFilter;

import javax.annotation.Resource;

/**
 * 自定义 Spring Security 适配器
 *
 * @author yeyue
 * @date 2022-04-19 10:28:03
 */
@Configuration
public class YeyueWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Resource
    YeyueUserDetailsAuthenticationProvider authenticationProvider;

    @Resource
    AuthenticationEntryPoint authenticationEntryPoint;

    @Resource
    AccessDeniedHandler accessDeniedHandler;

    @Resource
    JwtAuthenticationTokenFilter authenticationTokenFilter;

    /**
     * 认证管理器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    /**
     * 由于 Spring Security 创建 AuthenticationManager 对象时，没声明 @Bean 注解，导致无法被注入
     * 通过覆写父类的该方法，添加 @Bean 注解，解决该问题
     */
    @Override
    @Bean
    @ConditionalOnMissingBean(AuthenticationManager.class)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 跨域 开启
                .cors().and()
                // 跨站请求伪造 关闭
                .csrf().disable()
                // Session 关闭
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .headers().frameOptions().disable().and()
                // 添加自定义的处理器
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler).and()
                .authorizeRequests()
                // 静态资源放行
                .antMatchers(HttpMethod.GET, "/*.html", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                // 健康检查放行
                .antMatchers("/actuator/**", "/admin-actuator/**").permitAll()
                // Swagger放行
                .antMatchers(HttpMethod.GET, "/v3/api-docs", "/swagger-resources").permitAll()
                .antMatchers("/ruoyi/test/security/login").permitAll()
                // druid放行
                .antMatchers("/druid/**").permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);


    }
}
