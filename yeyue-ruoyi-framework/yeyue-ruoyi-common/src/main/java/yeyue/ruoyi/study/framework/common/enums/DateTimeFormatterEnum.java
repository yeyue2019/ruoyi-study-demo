package yeyue.ruoyi.study.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

/**
 * 时间格式枚举
 *
 * @author yeyue
 * @date 2022-04-08 21:13:26
 */
@Getter
@AllArgsConstructor
public enum DateTimeFormatterEnum {

    LOCAL_DATE_DEFAULT("yyyy-MM-dd"),
    LOCAL_TIME_DEFAULT("HH:mm:ss"),
    LOCAL_DATE_TIME_DEFAULT("yyyy-MM-dd HH:mm:ss"),

    ;

    private final String pattern;

    public DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(this.pattern);
    }
}
