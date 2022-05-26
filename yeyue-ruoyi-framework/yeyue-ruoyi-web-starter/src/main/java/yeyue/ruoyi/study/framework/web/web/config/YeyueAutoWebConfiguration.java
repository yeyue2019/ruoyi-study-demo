package yeyue.ruoyi.study.framework.web.web.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import yeyue.ruoyi.study.framework.common.servlet.constants.ServletConstants;
import yeyue.ruoyi.study.framework.common.servlet.util.ServletUtils;
import yeyue.ruoyi.study.framework.web.web.filter.ServiceFilter;
import yeyue.ruoyi.study.framework.web.web.handler.GlobalExceptionHandler;
import yeyue.ruoyi.study.framework.web.web.interceptor.HttpRequestHandlerInterceptor;

import javax.annotation.Resource;

/**
 * @author yeyue
 * @date 2022-04-14 14:37:33
 */
@Configuration
public class YeyueAutoWebConfiguration implements WebMvcConfigurer {

    @Resource
    GlobalExceptionHandler exceptionHandler;

    /**
     * 创建 CorsFilter Bean，解决跨域问题
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterBean() {
        // 创建 CorsConfiguration 对象
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置访问源地址
        config.addAllowedOriginPattern(ServletConstants.CORS_ALLOW);
        // 设置访问源请求头
        config.addAllowedHeader(ServletConstants.CORS_ALLOW);
        // 设置访问源请求方法
        config.addAllowedMethod(ServletConstants.CORS_ALLOW);
        // 创建 UrlBasedCorsConfigurationSource 对象
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 对接口配置跨域设置
        source.registerCorsConfiguration(ServletConstants.PATTERN_ALL, config);
        return ServletUtils.createFilterBean(new CorsFilter(source), ServletConstants.CORS_FILTER_ORDER);
    }

    /**
     * 缓存请求信息的Filter
     */
    @Bean
    public FilterRegistrationBean<ServiceFilter> serviceFilterBean() {
        return ServletUtils.createFilterBean(new ServiceFilter(exceptionHandler),
                ServletConstants.SERVICE_FILTER_ORDER);
    }

    /**
     * 注册请求拦截器
     */
    @Bean
    public HttpRequestHandlerInterceptor httpInterceptor() {
        return new HttpRequestHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(this.httpInterceptor()).addPathPatterns(ServletConstants.PATTERN_ALL);
    }
}
