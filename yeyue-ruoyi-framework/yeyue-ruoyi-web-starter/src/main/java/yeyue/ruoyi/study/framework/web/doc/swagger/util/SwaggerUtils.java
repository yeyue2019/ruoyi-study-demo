package yeyue.ruoyi.study.framework.web.doc.swagger.util;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.http.HttpMethod;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import yeyue.ruoyi.study.framework.common.pojo.core.ErrorCode;
import yeyue.ruoyi.study.framework.common.servlet.constants.ServletConstants;
import yeyue.ruoyi.study.framework.web.doc.swagger.properties.YeyueSwaggerProperties;

import java.time.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * swaggerUI工具类
 *
 * @author yeyue
 * @date 2022-04-28 17:29:30
 */
public abstract class SwaggerUtils {

    public static Docket initDocket(String groupName, Predicate<RequestHandler> selector, YeyueSwaggerProperties properties, OpenApiExtensionResolver resolver, ErrorCode[] codes) {
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
                .apis(selector)
                .paths(PathSelectors.any())
                .build()
                .groupName(groupName)
                .globalRequestParameters(globalRequestParameters())
                .extensions(resolver.buildSettingExtensions());
    }

    /**
     * 主页根据配置文件读取
     */
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
    private static List<Response> globalResponses(ErrorCode[] codes) {
        return Arrays.stream(codes).map(
                errorEnums -> new ResponseBuilder()
                        .code(errorEnums.getCode())
                        .description(errorEnums.getMsg())
                        .build()).collect(Collectors.toList());
    }

    /**
     * 全局通用请求参数
     */
    private static List<RequestParameter> globalRequestParameters() {
        RequestParameterBuilder tenantParameter = new RequestParameterBuilder().name(ServletConstants.AUTHORIZATION_HEADER).description("鉴权信息").required(false)
                .in(ParameterType.HEADER);
        return Collections.singletonList(tenantParameter.build());
    }
}
