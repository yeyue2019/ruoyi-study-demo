# ruoyi-study-demo

### 介绍

若依单体项目脚手架，通过学习[芋艿增强若依项目](https://github.com/YunaiV/ruoyi-vue-pro) 的项目架构和应用的技术栈，从无到有落地适用于自身应用场景的脚手架，逐步丰富其功能。

* **

### 软件架构

Springboot + Maven 构建为单体项目

#### 版本说明

| 组件名称         | 组件版本    | 项目引入版本 |
|:-------------|---------|--------|
| springboot   | 2.5.10  | 1.0.0  |
| redisson     | 3.16.6  | 1.0.0  |
| mybatis-plus | 3.4.3.4 | 1.0.0  |
| druid        | 1.2.8   | 1.0.0  |
| knife4j      | 3.0.2   | 1.0.0  |
| log4j2       | 2.17.1  | 1.0.0  |
| jackson      | 2.12.6  | 1.0.0  |
| tomcat       | 9.0.58  | 1.0.0  |
| skywalking   | 8.7.0   | 1.0.0  |

### 代码提交备忘

**2022-04-11** _v1.0.0_

```text
1. 日志框架从logback改为log4j2
2. Redisson作为Redis客户端操作框架，并且使用RedissonClient代替RedisTemplate， 为保证后期可能存在的切换场景，现将所有Redis交互操作都封装到一个Repository
3. MyBatis-Plus作为ORM框架，引入批量插入的方法(只支持MySQL这块)
   ，改良实体超类日期类型使用Java8新增的Time类，优化了自己实现的QueryWrapper实现类，利用QueryWrapper自身提供的condition作为条件约束，提供了一个简单的增删改查(含分页查询)的demo1，关闭其自身的日志输出，实现字段自动填充，扩展Mapper的功能
4. 数据库连接池使用druid-starter, 暂不使用dynamic-datasource-spring-boot-starter作为解决方案(存在B2C场景再去考虑)，简化druid的执行日志输出,过滤监控页面的广告
5. 引入参数自动校验的功能，将参数校验放在controller层作为实现(放在Service层存在扫描不到分组的问题)；可以实现自定义的参数校验器，参照文中实现即可
6. 优化Swagger增强框架knife4j，knife4j3以上的版本UI需要对应为OAS_30其增强配置才能生效，针对UI添加了许多增强性的功能配置，同时将通用错误枚举输出到接口文档中
7. 使用Jackson作为Web项目接收数据和返回数据的序列化方案，同时改造引入的Java8TimeModule，使用便于读写的时间序列化方案
8. 使用全局异常处理器，并且添加对于了Filter的异常处理(无法直接捕捉，后续可以考虑改良方案)
9. 不使用hu-tool作为工具类方案，太重了！引入common-lang3提供的一部分工具类和扩展spring提供的一些工具类作为工具类解决方案，所有的工具类使用虚拟类的方案避免其实例化，引入lombok全局配置的策略， 使用MapStruct进行对象转化
```

**2022-04-15** _v1.0.0_

```text
1. 引入链路追踪中间件，能够进行链路追踪，trace在上报的同时也可以输出到log4j2的日志里，添加自定义Tag到UI层面展示，显示sql执行的具体参数，忽略部分心跳的上报
2. 封装TracerUtils工具类，隐藏内部实现，便于后期切换追踪框架
3. 统一异常处理部分进行了优化，优化了一些错误的msg的展示交互，也优化了sql异常的信息格式
4. 优化了项目包结构，引进了hu-tool的部分工具类实现
5. web模块添加Filter,Interceptor,Advice等模块,实现输入输出请求流的拷贝复用,预留系统可拓展功能点
```

### 下一步集成的功能

1. 健康检查组件引入
2. 权限拦截框架学习
3. 安全控制学习

### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

### 学习不易，跪求大佬Star!!!


