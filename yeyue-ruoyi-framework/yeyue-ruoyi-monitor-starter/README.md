### SkyWalking需要进行的额外配置

1. 启用忽略追踪插件

```shell
cp optional-plugins/apm-trace-ignore-plugin-8.7.0.jar plugins/

vi config/apm-trace-ignore-plugin.config

# If the operation name of the first span is matching, this segment should be ignored
#  ant path match style
#  /path/?   Match any single character
#  /path/*   Match any number of characters
#  /path/**  Match any number of characters and support multilevel directories
#  Multiple path comma separation, like trace.ignore_path=/eureka/**,/consul/**
trace.ignore_path=${SW_AGENT_TRACE_IGNORE_PATH:/eureka/**}
```

2. 打开JDBC输出SQL的注释并且通过环境变量配置

plugin.jdbc.trace_sql_parameters=${SW_JDBC_TRACE_SQL_PARAMETERS:true}

3. 添加线程池追踪增强插件

```shell
cp bootstrap-plugins/apm-jdk-threading-plugin-8.7.0.jar plugins

# 如果没有，添加配置

# 线程类 (java.lang.Runnable和java.util.concurrent.Callable) 及其子类，包括名称与THREADING_CLASS_PREFIXES( 分割,) 中的任何一个匹配的匿名内部类，将被检测，确保只指定您期望检测的窄前缀， (java.并将javax.被忽略由于安全问题）
plugin.jdkthreading.threading_class_prefixes=${THREADING_CLASS_PREFIXES:}
```