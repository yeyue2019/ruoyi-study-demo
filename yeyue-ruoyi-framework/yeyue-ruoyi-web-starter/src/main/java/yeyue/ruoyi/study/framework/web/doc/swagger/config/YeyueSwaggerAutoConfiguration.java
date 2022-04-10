package yeyue.ruoyi.study.framework.web.doc.swagger.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.web.doc.swagger.properties.YeyueSwaggerProperties;

import java.util.*;
import java.util.stream.Collectors;

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
        // 创建 Docket 对象
        return new Docket(DocumentationType.OAS_30)
                // 用来创建该 API 的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo(properties))
                .globalResponses(HttpMethod.GET, globalResponses())
                .globalResponses(HttpMethod.POST, globalResponses())
                .globalResponses(HttpMethod.DELETE, globalResponses())
                .globalResponses(HttpMethod.PUT, globalResponses())
                .useDefaultResponseMessages(false)
                // 设置扫描指定 package 包下的
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildSettingExtensions())
                // .securitySchemes(securitySchemes())
                // .globalRequestParameters(globalRequestParameters())
                // .securityContexts(securityContexts())
                ;
    }

    private static ApiInfo apiInfo(YeyueSwaggerProperties properties) {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .contact(new Contact(properties.getAuthor(), null, null))
                .version(properties.getVersion())
                .build();
    }

    /**
     * 获取全局响应状态码
     */
    public static List<Response> globalResponses() {
        return Arrays.stream(GlobalErrorCode.values()).map(
                errorEnums -> new ResponseBuilder()
                        .code(errorEnums.getCode())
                        .description(errorEnums.getMsg())
                        .build()).collect(Collectors.toList());
    }
}
