package yeyue.ruoyi.study.module.infra.impl.framework.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import yeyue.ruoyi.study.framework.common.pojo.core.ErrorCode;

/**
 * 系统错误码枚举
 *
 * @author yeyue
 * @date 2022-04-28 17:14:05
 */
@Getter
@AllArgsConstructor
public enum InfraErrorCode implements ErrorCode {

    ;

    private final String code;
    private final String msg;
}
