package yeyue.ruoyi.study.framework.web.json.jackson.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.*;
import com.fasterxml.jackson.datatype.jsr310.ser.*;
import org.springframework.context.annotation.*;
import yeyue.ruoyi.study.framework.common.enums.DateTimeFormatterEnum;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * jackson序列化配置
 *
 * @author yeyue
 * @date 2022-04-09 17:30:42
 */
@Configuration
public class YeyueJacksonAutoConfiguration {

    // 提高JavaTimeModule Bean的优先级

    @Primary
    @Bean
    public JavaTimeModule javaTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // 处理LocalDateTime
        DateTimeFormatter dateTimeFormatter = DateTimeFormatterEnum.LOCAL_DATE_TIME_DEFAULT.getFormatter();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        // 处理LocalDate
        DateTimeFormatter dateFormatter = DateTimeFormatterEnum.LOCAL_DATE_DEFAULT.getFormatter();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        // 处理LocalTime
        DateTimeFormatter timeFormatter = DateTimeFormatterEnum.LOCAL_TIME_DEFAULT.getFormatter();
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
        return javaTimeModule;
    }

}
