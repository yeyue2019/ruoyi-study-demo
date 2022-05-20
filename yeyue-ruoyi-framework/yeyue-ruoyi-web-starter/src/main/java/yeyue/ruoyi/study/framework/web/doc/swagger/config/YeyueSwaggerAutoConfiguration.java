package yeyue.ruoyi.study.framework.web.doc.swagger.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.web.doc.swagger.properties.YeyueSwaggerProperties;
import yeyue.ruoyi.study.framework.web.doc.swagger.util.SwaggerUtils;

/**
 * Swagger Doc自动配置
 *
 * @author yeyue
 * @date 2022-04-10 15:01:37
 */
@Configuration
@EnableSwagger2
@ConditionalOnClass({Docket.class, ApiInfoBuilder.class})
@ConditionalOnProperty(prefix = "swagger", name = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(YeyueSwaggerProperties.class)
public class YeyueSwaggerAutoConfiguration {

    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public YeyueSwaggerAutoConfiguration(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean
    public Docket globalDocket(YeyueSwaggerProperties properties) {
        return SwaggerUtils.initDocket(null, RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class), properties, openApiExtensionResolver, GlobalErrorCode.values());
    }
}
