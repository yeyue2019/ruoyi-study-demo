package yeyue.ruoyi.study.module.system.impl.framework.swagger;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import yeyue.ruoyi.study.framework.common.pojo.core.ErrorCode;
import yeyue.ruoyi.study.framework.web.doc.swagger.config.YeyueSwaggerAutoConfiguration;
import yeyue.ruoyi.study.framework.web.doc.swagger.properties.YeyueSwaggerProperties;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;

import java.time.*;

import static yeyue.ruoyi.study.framework.web.doc.swagger.util.SwaggerUtils.*;

/**
 * @author yeyue
 * @date 2022-04-28 17:35:00
 */
@Configuration("systemSwaggerConfiguration")
@ConditionalOnBean(YeyueSwaggerAutoConfiguration.class)
public class SwaggerConfiguration {

    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public SwaggerConfiguration(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean
    public Docket systemDocket(YeyueSwaggerProperties properties) {
        ErrorCode[] codes = SystemErrorCode.values();
        // 创建 Docket 对象
        return new Docket(DocumentationType.OAS_30)
                // 用来创建该 API 的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo(properties))
                .globalResponses(HttpMethod.GET, globalResponses(codes))
                .globalResponses(HttpMethod.POST, globalResponses(codes))
                .globalResponses(HttpMethod.DELETE, globalResponses(codes))
                .globalResponses(HttpMethod.PUT, globalResponses(codes))
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalTime.class, String.class)
                .directModelSubstitute(LocalDateTime.class, String.class)
                .useDefaultResponseMessages(false)
                // default配置默认读取所有接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("yeyue.ruoyi.study.module.system.impl.controller"))
                .paths(PathSelectors.any())
                .build()
                .groupName("system")
                .globalRequestParameters(globalRequestParameters())
                .extensions(openApiExtensionResolver.buildSettingExtensions());
    }
}
