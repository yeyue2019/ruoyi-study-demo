package yeyue.ruoyi.study.framework.security.core.userdetails;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;

import java.util.*;

/**
 * web登录用户
 *
 * @author yeyue
 * @date 2022-04-19 13:37:09
 */
@Data
public class WebLoginUser implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private Integer status;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    // 不依赖 Security的判断

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @Override
    public boolean isEnabled() {
        return CommonStatusEnum.ENABLE.getStatus().equals(this.status);
    }
}
