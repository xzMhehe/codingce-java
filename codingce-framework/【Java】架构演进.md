# Java架构演进

## 单体架构概述

**单体架构的优点**：小团队成型即可完成开发-测试-上线；迭代周期短，速度快；打包方便，运维省事。

单体部署：Tomcat-webapp、Tomcat 静态资源、MariaDB 单节点。

**单体架构面临的挑战**：单节点宕机造成所有服务不可用；耦合度太高(迭代，测试，部署)；单节点并发能力有限。

**集群概念**：计算机“群体’构成整个系统；这个“群体’构成一个整体，不能独立存在；人多力量大群体**提升并发**与**可用性**。

**项目改进**：将项目分层 **订单业务** **商品业务** **其他业务** **订单集群** **商品集群**，一旦某个订单节点宕机 还可以访问订单集群的其他节点。

**使用集群的优势**：提高系统性能；提高系统可用性；可扩展性高。

**使用集群的注意点**：用户会话；定时任务；内网互通。

## 单体架构设计及准备工作

从单体架构项目开始进行架构设计和准备工作，如下图：

![](https://raw.githubusercontent.com/xzMhehe/StaticFile_CDN/main/static/img/mo/20230316131609.png)

### 技术选型

技术选型:

- 前后端分离，前端使用 vue jquery
- 后端技术：Spring Boot MyBatis Spring
- 数据库：MySQL

### 项目分层设计

对项目进行分层的处理，每层处理的每个层的逻辑使项目更加方便于维护。

common层：负责通用的工具类和固定的一些枚举；

pojo层：负责数据库映射的pojo类，以及 vo 定义返回给前端数据接口，bo定义前端传递给后端的数据接口；（引入common层）

mapper层：负责 dao 以及 mapper.xml 的实现逻辑，负责操作数据库；（引入pojo层）

service层：负责业务逻辑的实现操作mapper；（引入mapper层）

api层：负责api接口层实现及整体配置。（引入service层）

#### 聚合项目搭建

创建一个Maven的空项目，不要选择Create from archetype

然后在主项目中依次创建 module

![](https://raw.githubusercontent.com/xzMhehe/StaticFile_CDN/main/static/img/mo/20230316134734.png)

主项目的 pom.xml 配置，引入创建的几个module

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.6.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>

<groupId>com.bsh.lable</groupId>
<artifactId>bsh-lable</artifactId>
<version>0.0.1-SNAPSHOT</version>
<packaging>pom</packaging>

<name>bsh-lable</name>
<description>Demo project for Spring Boot</description>

<properties>
    <!-- 统一定义版本号 -->
    <lable.version>0.0.1-SNAPSHOT</lable.version>
    <java.version>1.8</java.version>
    <pagehelper.version>1.4.0</pagehelper.version>
</properties>

<modules>
    <module>lable-common</module>
    <module>lable-system</module>
    <module>lable-framework</module>
    <module>lable-admin</module>
</modules>

<dependencyManagement>

    <dependencies>
        <!-- 子模块 -->
        <dependency>
            <groupId>com.bsh.lable</groupId>
            <artifactId>lable-common</artifactId>
            <version>${lable.version}</version>
        </dependency>

        <dependency>
            <groupId>com.bsh.lable</groupId>
            <artifactId>lable-system</artifactId>
            <version>${lable.version}</version>
        </dependency>

        <dependency>
            <groupId>com.bsh.lable</groupId>
            <artifactId>lable-framework</artifactId>
            <version>${lable.version}</version>
        </dependency>

        <dependency>
            <groupId>com.bsh.lable</groupId>
            <artifactId>lable-admin</artifactId>
            <version>${lable.version}</version>
        </dependency>

    </dependencies>
</dependencyManagement>
```



lable-common

```xml
<parent>
    <groupId>com.bsh.lable</groupId>
    <artifactId>bsh-lable</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</parent>

<artifactId>lable-common</artifactId>
<name>lable-common</name>
<description>common</description>

<properties>
    <java.version>1.8</java.version>
</properties>

<dependencies>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    ...
</dependencies>
```

lable-system

```xml
<parent>
    <groupId>com.bsh.lable</groupId>
    <artifactId>bsh-lable</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</parent>

<artifactId>lable-system</artifactId>
<version>0.0.1-SNAPSHOT</version>
<name>lable-system</name>
<description>system</description>

<properties>
    <java.version>1.8</java.version>
</properties>

<dependencies>

    <dependency>
        <groupId>com.bsh.lable</groupId>
        <artifactId>lable-common</artifactId>
    </dependency>

</dependencies>
```



其它层层层引入。

#### 数据库设计

注意，项目为了演进架构，部分数据表的主键 ID 都是用 Varchar 类型，因为项目以后会演进到分布式还设计到分库分表等，每个主键设计为唯一主键ID。

不用外键，可能影响性能，影响后期项目分库分表









