package yeyue.ruoyi.study.framework.common.pojo.core;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.io.Serializable;

import static yeyue.ruoyi.study.framework.common.constants.CommonConstants.CODE_SUCCESS_STR;

/**
 * 错误码
 *
 * @author yeyue
 * @date 2022-04-08 20:06:28
 */
public interface ErrorCode extends Serializable {

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
    static String assertError(String code) {
        Assert.isTrue(!isSuccess(code), "code 必须是错误的！");
        return code;
    }

    /**
     * 确认错误场景的错误码
     */
    static String assertError(Integer code) {
        Assert.notNull(code, "code 不能为空！");
        return assertError(String.valueOf(code));
    }

    /**
     * 是否成功
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @ApiModelProperty(hidden = true)
    default boolean isSuccess() {
        return !isSuccess(getCode());
    }

    /**
     * 是否失败
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @ApiModelProperty(hidden = true)
    default boolean isError() {
        return !isSuccess();
    }
}
