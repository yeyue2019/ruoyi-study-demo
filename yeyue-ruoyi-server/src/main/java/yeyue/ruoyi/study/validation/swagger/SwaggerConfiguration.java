package yeyue.ruoyi.study.validation.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

/**
 * @author yeyue
 * @date 2022-04-09 01:01:28
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        // 创建 Docket 对象
        return new Docket(DocumentationType.SWAGGER_2)
                // 用来创建该 API 的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                // 设置扫描指定 package 包下的
                .select()
                .apis(basePackage("yeyue.ruoyi.study.validation")) // 可用于 swagger 无法展示时使用
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * API 摘要信息
     */
    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger")
                .build();
    }
}
