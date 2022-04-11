package yeyue.ruoyi.study.framework.common.pojo;

import io.swagger.annotations.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.core.ErrorCode;

import static yeyue.ruoyi.study.framework.common.constants.CommonConstants.CODE_SUCCESS_STR;

/**
 * 结果返回
 *
 * @author yeyue
 * @date 2022-04-08 17:11:38
 */
@Data
@ApiModel
public class CommonResult<T> implements ErrorCode {

    @ApiModelProperty(value = "错误码")
    private String code;

    @ApiModelProperty(value = "数据")
    private T data;

    @ApiModelProperty(value = "错误信息")
    private String msg;

    /**
     * 成功结果
     */
    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = CODE_SUCCESS_STR;
        result.data = data;
        return result;
    }

    /**
     * 成功结果
     */
    public static <T> CommonResult<T> success() {
        CommonResult<T> result = new CommonResult<>();
        result.code = CODE_SUCCESS_STR;
        return result;
    }

    /**
     * 错误结果
     */
    public static <T> CommonResult<T> error(String code, String message) {
        ErrorCode.assertError(code);
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.msg = message;
        return result;
    }

    /**
     * 错误结果
     */
    public static <T> CommonResult<T> error(Integer code, String message) {
        ErrorCode.assertError(code);
        return error(String.valueOf(code), message);
    }

    /**
     * 错误结果
     */
    public static <T> CommonResult<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    /**
     * 错误结果
     */
    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.code, result.msg);
    }
}
