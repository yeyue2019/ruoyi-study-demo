package yeyue.ruoyi.study.framework.monitor.trace.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import yeyue.ruoyi.study.framework.common.constants.CommonConstants;
import yeyue.ruoyi.study.framework.common.servlet.util.ServletUtils;
import yeyue.ruoyi.study.framework.monitor.trace.filter.YeyueTraceFilter;

/**
 * 日志自动配置
 *
 * @author yeyue
 * @date 2022-04-13 16:16:14
 */
@Configuration
public class YeyueTraceAutoConfiguration {

    @Bean
    public FilterRegistrationBean<YeyueTraceFilter> traceFilter() {
        return ServletUtils.createFilterBean(new YeyueTraceFilter(), CommonConstants.TRACE_FILTER_ORDER);
    }
}
