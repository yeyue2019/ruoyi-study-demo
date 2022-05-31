package yeyue.ruoyi.study.framework.druid.core.enums;

/**
 * 多数据源的枚举类
 *
 * @author yeyue
 * @date 2022-04-10 17:36:07
 */
public interface DataSourceEnum {

    /**
     * 主库，推荐使用 {@link com.baomidou.dynamic.datasource.annotation.Master} 注解
     */
    String MASTER = "master";
    /**
     * 从库，推荐使用 {@link com.baomidou.dynamic.datasource.annotation.Slave} 注解
     */
    String SLAVE = "slave";

}
