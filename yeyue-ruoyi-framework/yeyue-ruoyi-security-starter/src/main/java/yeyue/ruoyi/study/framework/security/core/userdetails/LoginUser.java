package yeyue.ruoyi.study.framework.security.core.userdetails;

import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * 登录用户
 *
 * @author yeyue
 * @date 2022-04-19 13:37:09
 */
@Data
public class LoginUser implements Serializable {

    /**
     * 用户Id
     */
    private String id;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 授权范围
     */
    private List<String> scopes;

    /**
     * 上下文字段
     */
    private Map<String, Object> context;

    public void setContext(String key, Object value) {
        if (context == null) {
            context = new HashMap<>(10);
        }
        context.put(key, value);
    }

    public Object getContext(String key) {
        if (context == null) {
            return null;
        }
        return context.get(key);
    }
}
