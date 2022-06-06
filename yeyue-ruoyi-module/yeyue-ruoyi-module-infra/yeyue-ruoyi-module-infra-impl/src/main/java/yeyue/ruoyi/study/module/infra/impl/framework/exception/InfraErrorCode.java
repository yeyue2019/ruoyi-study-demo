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
    JOB_HANDLER_EXISTS("020101", "定时任务的处理器已经存在"),
    JOB_NOT_EXISTS("020102", "定时任务不存在"),
    JOB_UPDATE_ONLY_ENABLE("020103", "开启状态的定时任务才能修改"),

    ;

    private final String code;
    private final String msg;
}
