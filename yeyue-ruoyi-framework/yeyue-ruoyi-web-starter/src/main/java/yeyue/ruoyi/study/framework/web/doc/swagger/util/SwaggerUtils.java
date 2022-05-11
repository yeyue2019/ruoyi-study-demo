package yeyue.ruoyi.study.framework.web.doc.swagger.util;

import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import yeyue.ruoyi.study.framework.common.pojo.core.ErrorCode;
import yeyue.ruoyi.study.framework.common.servlet.constants.ServletConstants;
import yeyue.ruoyi.study.framework.web.doc.swagger.properties.YeyueSwaggerProperties;

import java.util.*;
import java.util.stream.Collectors;

/**
 * swaggerUI工具类
 *
 * @author yeyue
 * @date 2022-04-28 17:29:30
 */
public abstract class SwaggerUtils {

    /**
     * 主页根据配置文件读取
     */
    public static ApiInfo apiInfo(YeyueSwaggerProperties properties) {
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
    public static List<Response> globalResponses(ErrorCode[] codes) {
        return Arrays.stream(codes).map(
                errorEnums -> new ResponseBuilder()
                        .code(errorEnums.getCode())
                        .description(errorEnums.getMsg())
                        .build()).collect(Collectors.toList());
    }

    /**
     * 全局通用请求参数
     */
    public static List<RequestParameter> globalRequestParameters() {
        RequestParameterBuilder tenantParameter = new RequestParameterBuilder().name(ServletConstants.AUTHORIZATION_HEADER).description("鉴权信息").required(false)
                .in(ParameterType.HEADER);
        return Collections.singletonList(tenantParameter.build());
    }
}
