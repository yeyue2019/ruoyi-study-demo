package yeyue.ruoyi.study.framework.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

import yeyue.ruoyi.study.framework.mybatis.core.handler.MyBatisFieldAutoFillHandler;
import yeyue.ruoyi.study.framework.mybatis.core.injector.MyBatisSqlInjector;

/**
 * @author yeyue
 * @date 2022-04-09 23:36:50
 */
@Configuration
@MapperScan(basePackages = {"yeyue.ruoyi.study.**.mapper.**"})
public class YeyueMyBatisAutoConfiguration {

    // TODO: 2022/4/21 扫描的包使用配置文件指定

    /**
     * 使用分页插件
     */
    @Bean
    @Primary
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mybatisPlusInterceptor;
    }

    /**
     * 拦截填充信息
     */
    @Bean
    @Primary
    public MetaObjectHandler metaObjectHandler() {
        return new MyBatisFieldAutoFillHandler();
    }

    /**
     * 开启批量操作
     */
    @Bean
    @Primary
    public MyBatisSqlInjector myBatisSqlInjector() {
        return new MyBatisSqlInjector();
    }
}
