package yeyue.ruoyi.study.framework.quartz.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author yeyue
 * @date 2022-05-31 21:26:00
 */
@Configuration
public class YeyueQuartzAutoConfiguration {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Order(1)
    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer(DataSource dataSource) {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource quartz = ds.getDataSource("quartz");
        return schedulerFactoryBean -> {
            schedulerFactoryBean.setDataSource(quartz);
            schedulerFactoryBean.setTransactionManager(new DataSourceTransactionManager(quartz));
        };
    }
}
