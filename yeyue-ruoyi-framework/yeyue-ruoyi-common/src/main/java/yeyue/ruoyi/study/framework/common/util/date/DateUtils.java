package yeyue.ruoyi.study.framework.common.util.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.validation.constraints.NotNull;

import yeyue.ruoyi.study.framework.common.enums.DateTimeFormatterEnum;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.common.util.object.ObjectUtils;

/**
 * 日期处理工具
 *
 * @author yeyue
 * @date 2022-04-08 21:08:40
 */
public abstract class DateUtils {

    // Date 和 LocalDateTime 转化

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return ObjectUtils.convert(date, null, dt -> LocalDateTime.ofInstant(dt.toInstant(), ZoneId.systemDefault()));
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return ObjectUtils.convert(localDateTime, null, dt -> Date.from(dt.atZone(ZoneId.systemDefault()).toInstant()));
    }

    // 字符串 和 LocalDateTime 转化

    public static LocalDateTime stringToLocalDateTime(String string, @NotNull DateTimeFormatter format) {
        return ObjectUtils.convert(string, null, str -> LocalDateTime.parse(str, format));
    }

    public static LocalDateTime stringToLocalDateTime(String string, @NotNull DateTimeFormatterEnum formatterEnum) {
        return stringToLocalDateTime(string, formatterEnum.getFormatter());
    }

    public static String localDateTimeToString(LocalDateTime localDateTime, @NotNull DateTimeFormatter formatter) {
        return ObjectUtils.convert(localDateTime, null, ldt -> ldt.format(formatter));
    }

    public static String localDateTimeToString(LocalDateTime localDateTime,
        @NotNull DateTimeFormatterEnum formatterEnum) {
        return ObjectUtils.convert(localDateTime, null, ldt -> ldt.format(formatterEnum.getFormatter()));
    }

    // Date 和 字符串 转化

    public static Date stringToDate(String string, @NotNull DateTimeFormatter format) {
        return localDateTimeToDate(stringToLocalDateTime(string, format));
    }

    public static Date stringToDate(String string, @NotNull DateTimeFormatterEnum formatterEnum) {
        return localDateTimeToDate(stringToLocalDateTime(string, formatterEnum));
    }

    public static String dateToString(Date date, @NotNull DateTimeFormatter format) {
        return localDateTimeToString(dateToLocalDateTime(date), format);
    }

    public static String dateToString(Date date, @NotNull DateTimeFormatterEnum formatterEnum) {
        return localDateTimeToString(dateToLocalDateTime(date), formatterEnum);
    }

    // 时间戳 和 Date 转化

    public static Date timeStampToDate(Long timeStamp) {
        return ObjectUtils.convert(timeStamp, null, ts -> {
            int length = String.valueOf(ts).length();
            long target;
            switch (length) {
                case 10:
                    // 秒
                    target = ts * 1000L;
                    break;
                case 13:
                    // 毫秒
                    target = ts;
                    break;
                case 16:
                    // 纳秒
                    target = ts / 1000;
                    break;
                default:
                    throw new ServiceException(GlobalErrorCode.DATA_NOT_MATCH_ENUMS);
            }
            return new Date(target);
        });
    }

    public static Long dateToTimeStamp(Date date, int timeStampSize) {
        return ObjectUtils.convert(date, null, dt -> {
            long target = dt.toInstant().toEpochMilli();
            switch (timeStampSize) {
                case 10:
                    return target / 1000L;
                case 13:
                    return target;
                case 16:
                    return target * 1000L;
                default:
                    throw new ServiceException(GlobalErrorCode.DATA_NOT_MATCH_ENUMS);
            }
        });
    }

    // 时间戳 和 LocalDateTime 转化

    public static LocalDateTime timeStampToLocalDateTime(Long timeStamp) {
        return dateToLocalDateTime(timeStampToDate(timeStamp));
    }

    public static Long localDateTimeToTimeStamp(LocalDateTime localDateTime, int timeStampSize) {
        return dateToTimeStamp(localDateTimeToDate(localDateTime), timeStampSize);
    }

    // 时间戳 和 字符串 转化

    public static String timeStampToString(Long timeStamp, @NotNull DateTimeFormatter format) {
        return dateToString(timeStampToDate(timeStamp), format);
    }

    public static String timeStampToString(Long timeStamp, @NotNull DateTimeFormatterEnum formatterEnum) {
        return dateToString(timeStampToDate(timeStamp), formatterEnum);
    }

    public static Long stringToTimeStamp(String string, @NotNull DateTimeFormatter format, int timeStampSize) {
        return dateToTimeStamp(stringToDate(string, format), timeStampSize);
    }

    public static Long stringToTimeStamp(String string, @NotNull DateTimeFormatterEnum formatterEnum,
        int timeStampSize) {
        return dateToTimeStamp(stringToDate(string, formatterEnum), timeStampSize);
    }

}
