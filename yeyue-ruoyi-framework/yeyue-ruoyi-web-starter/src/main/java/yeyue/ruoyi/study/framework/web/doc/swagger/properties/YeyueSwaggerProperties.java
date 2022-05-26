package yeyue.ruoyi.study.framework.web.doc.swagger.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Swagger Doc配置项
 *
 * @author yeyue
 * @date 2022-04-10 14:57:36
 */
@Data
@ConfigurationProperties("swagger")
public class YeyueSwaggerProperties {

    /**
     * 是否开启Swagger
     */
    private boolean enable = true;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 作者
     */
    private String author;

    /**
     * 版本
     */
    private String version;
}
