package yeyue.ruoyi.study.framework.druid.config;

import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yeyue.ruoyi.study.framework.common.util.object.ObjectUtils;
import yeyue.ruoyi.study.framework.druid.core.filter.DruidAdRemoveFilter;

/**
 * durid的配置改良
 *
 * @author yeyue
 * @date 2022-04-10 17:34:19
 */
@Configuration
public class YeyueDruidAutoConfiguration {

    // 去掉连接超时后的warn警告
    static {
        System.setProperty("druid.mysql.usePingMethod", "false");
    }

    /**
     * 创建 DruidAdRemoveFilter 过滤器，过滤 common.js 的广告
     */
    @Bean
    @ConditionalOnProperty(name = "spring.datasource.druid.stat-view-servlet.enabled", havingValue = "true")
    public FilterRegistrationBean<DruidAdRemoveFilter> druidAdRemoveFilterFilter(DruidStatProperties properties) {
        // 获取 druid web 监控页面的参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
        // 提取 common.js 的配置路径
        String pattern = ObjectUtils.defaultIfNull(config.getUrlPattern(), "/druid/*");
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
        // 创建 DruidAdRemoveFilter Bean
        FilterRegistrationBean<DruidAdRemoveFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new DruidAdRemoveFilter());
        registrationBean.addUrlPatterns(commonJsPattern);
        return registrationBean;
    }
}
