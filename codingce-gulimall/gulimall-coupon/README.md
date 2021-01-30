# 微服务的注册中心、微服务的配置中心

# 如何使用 nacos 作为配置中心, 统一管理配置
- 引入依赖
```xml
 <dependency>
     <groupId>com.alibaba.cloud</groupId>
     <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
 </dependency>
```

 - 创建一个 bootstrap.proprties
```yaml
spring.application.name=gulimall-coupon
# 指定 nacos 配置中心地址
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
```

- 需要给配置中心默认添加一个叫 数据集(Data Id) gulimall-coupon.properties 默认规则    应用名.properties    
然后给 应用名.properties 添加任何配置

- 动态获取配置
    - @RefreshScope
    - @Value(${配置的名称}) 获取配置的值

如果配置中心和当前应用的配置文件都配置了相同的项, 优先使用配置中心的


# 细节
## 命名空间  `配置隔离`
- 默认 public(保留空间) , 默认新增的所有的配置都在 public 空间
- 开发、测试、生产
- 利用命名空间来做环境隔离 配置中心环境切换 spring.cloud.nacos.config.namespace=1aac2a28-9329-4b60-93f0-dbf42e8da5dd  
- 注意在 bootstrap.properties 配置上需要使用哪个命名空间下的配置
- 每一个微服务之间相互隔离配置, 每一个微服务都创建自己的命名空间, 加载自己的命名空间下的所有配置
    
## 配置集
一组相关或者不相关的配置项的集合称为配置集。在系统中，一个配置文件通常就是一个配置集，包含了系统各个方面的配置。   
例如，一个配置集可能包含了数据源、线程池、日志级别等配置项。

## 配置集 ID
Data Id  类似文件名    
Nacos中的某个配置集的ID。配置集ID是组织划分配置的维度之一。DataID通常用于组织划分系统的配置集。
一个系统或者应用可以包含多个配置集，每个配置集都可以被一个有意义的名称标识。DataID通常采用类Java包
（如com.taobao.tc.refund.log.level）的命名规则保证全局唯一性。此命名规则非强制。

## 配置分组
默认所有的配置集都属于 `DEFAULT_GROUP`
例如 双十一  Group:1111   双十二 Group:1212  年货节 Group:nianhuojie   
    
bootstrap.properties 配置上需要使用哪个组, 不写的话默认 spring.cloud.nacos.config.group=DEFAULT_GROUP
spring.cloud.nacos.config.group=1111


# 同时加载多个数据集
- 微服务任何配置信息, 任何配置文件都可以放在配置中心
- 只需要在 bootstrap.properties 说明加载配置中心哪些配置文件
- @Value @ConfigurationProperties

以前 SpringBoot 任何方法从配置文件中获取值, 都能使用    
配置中心有优先使用配置中心的


```yaml
spring.cloud.nacos.config.shared-configs[0].data-id=datasource.yml
spring.cloud.nacos.config.shared-configs[0].group=dev
spring.cloud.nacos.config.shared-configs[0].refresh=true

spring.cloud.nacos.config.shared-configs[1].data-id=mybatis.yml
spring.cloud.nacos.config.shared-configs[1].group=dev
spring.cloud.nacos.config.shared-configs[1].refresh=true

spring.cloud.nacos.config.shared-configs[2].data-id=other.yml
spring.cloud.nacos.config.shared-configs[2].group=dev
spring.cloud.nacos.config.shared-configs[2].refresh=true
```

