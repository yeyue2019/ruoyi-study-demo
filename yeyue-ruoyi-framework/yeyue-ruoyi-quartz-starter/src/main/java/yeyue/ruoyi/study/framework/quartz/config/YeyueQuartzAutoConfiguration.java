package yeyue.ruoyi.study.framework.quartz.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import org.quartz.Scheduler;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import yeyue.ruoyi.study.framework.druid.core.enums.DataSourceEnum;
import yeyue.ruoyi.study.framework.quartz.core.scheduler.SchedulerManager;

import javax.sql.DataSource;

/**
 * @author yeyue
 * @date 2022-05-31 21:26:00
 */
@Configuration
@EnableScheduling
public class YeyueQuartzAutoConfiguration {

    @Order(1)
    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer(DataSource dataSource) {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource quartz = ds.getDataSource(DataSourceEnum.QUARTZ);
        return schedulerFactoryBean -> {
            schedulerFactoryBean.setDataSource(quartz);
            schedulerFactoryBean.setTransactionManager(new DataSourceTransactionManager(quartz));
        };
    }

    @Bean
    public SchedulerManager schedulerManager(Scheduler scheduler) {
        return new SchedulerManager(scheduler);
    }
}