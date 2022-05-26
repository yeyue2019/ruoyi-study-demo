package yeyue.ruoyi.study.framework.security.core.authorize;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 自定义URL安全配置
 *
 * @author yeyue
 * @date 2022-04-28 15:57:35
 */
public abstract class AuthorizeRequestsCustomizer
    implements Customizer<ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry> {}
