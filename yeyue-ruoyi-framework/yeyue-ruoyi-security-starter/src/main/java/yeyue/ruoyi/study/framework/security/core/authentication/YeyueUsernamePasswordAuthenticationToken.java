package yeyue.ruoyi.study.framework.security.core.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 自定义Token 实现类
 * 可以根据需求添加字段和构造函数
 *
 * @author yeyue
 * @date 2022-04-19 14:00:30
 */
public class YeyueUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public YeyueUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public YeyueUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
