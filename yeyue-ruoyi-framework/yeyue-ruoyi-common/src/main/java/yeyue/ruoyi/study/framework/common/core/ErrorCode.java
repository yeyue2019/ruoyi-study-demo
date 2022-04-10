package yeyue.ruoyi.study.framework.common.core;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * 错误码
 *
 * @author yeyue
 * @date 2022-04-08 20:06:28
 */
public interface ErrorCode extends Serializable {
    int CODE_SUCCESS_INT = 0;
    String CODE_SUCCESS_STR = String.valueOf(CODE_SUCCESS_INT);

    /**
     * 错误码
     */
    String getCode();

    /**
     * 错误提示
     */
    String getMsg();

    /**
     * code是否成功码
     */
    static boolean isSuccess(String code) {
        return StringUtils.equals(code, CODE_SUCCESS_STR);
    }

    /**
     * 确认错误场景的错误码
     */
    static void assertError(String code) {
        Assert.isTrue(!isSuccess(code), "code 必须是错误的！");
    }

    /**
     * 确认错误场景的错误码
     */
    static void assertError(Integer code) {
        Assert.notNull(code, "code 不能为空！");
    }

    // 不使用Is作为实例方法输出，防止被序列化和反序列化

    /**
     * 是否成功
     */
    default boolean ifSuccess() {
        return !isSuccess(getCode());
    }

    /**
     * 是否失败
     */
    default boolean ifError() {
        return !ifSuccess();
    }
}
