package yeyue.ruoyi.study.module.infra.impl.framework.swagger;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.Docket;
import yeyue.ruoyi.study.framework.web.doc.swagger.config.YeyueSwaggerAutoConfiguration;
import yeyue.ruoyi.study.framework.web.doc.swagger.properties.YeyueSwaggerProperties;
import yeyue.ruoyi.study.framework.web.doc.swagger.util.SwaggerUtils;
import yeyue.ruoyi.study.module.infra.impl.framework.exception.InfraErrorCode;

/**
 * @author yeyue
 * @date 2022-04-28 17:35:00
 */
@Configuration("infraSwaggerConfiguration")
@ConditionalOnBean(YeyueSwaggerAutoConfiguration.class)
public class SwaggerConfiguration {

    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public SwaggerConfiguration(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean(name = "infraDocket")
    public Docket infraDocket(YeyueSwaggerProperties properties) {
        return SwaggerUtils.initDocket("infra",
                RequestHandlerSelectors.basePackage("yeyue.ruoyi.study.module.infra.impl.controller"), properties,
                openApiExtensionResolver, InfraErrorCode.values());
    }
}
