package yeyue.ruoyi.study.framework.common.enums;

import lombok.*;

import java.time.format.DateTimeFormatter;

import static yeyue.ruoyi.study.framework.common.constants.CommonConstants.*;

/**
 * 时间格式枚举
 *
 * @author yeyue
 * @date 2022-04-08 21:13:26
 */
@Getter
@AllArgsConstructor
public enum DateTimeFormatterEnum {

    LOCAL_DATE_DEFAULT(DateTimeFormatter.ofPattern(DATE_PATTERN)),
    LOCAL_TIME_DEFAULT(DateTimeFormatter.ofPattern(TIME_PATTERN)),
    LOCAL_DATE_TIME_DEFAULT(DateTimeFormatter.ofPattern(DATETIME_PATTERN)),


    // TODO 其余时间格式等待使用的场景再进行补充
    ;

    private final DateTimeFormatter formatter;
}
