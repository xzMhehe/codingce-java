# Spring 事务

## Spring 事务管理

使用Spring事务处理机制处理开发中的事务。

### 什么是事务

事务一般特指数据库事务(Database Transaction)，是指作为一个程序执行单元执行的一系列操作，要么完全执行，要么完全不执行。

事务特性：

- 原子性(atomicity)：一个事务是一个不可分割的工作单位；
- 一致性(consistency)：事务必须是使数据库从一个一致性状态变到另一个一致性状态；

- - 业务的一致性，例如以商品订单为例：订单表中插入一条记录。购买了10件编号为1的商品，结果在update里给编号为1的商品修改了数量减1，这样的不符合业务规则，SQL语句执行没有问题原子性是符合的，所以一致性是从业务角度来分析的。

- 隔离性(isolation)：一个事务的执行不能被其他事务干扰。

- - 例如：并发的情况，其中一个事务生成订单，在订单表里面插入一条记录编号01的商品购买了10个。第二个修改库存数的时候还没有改，结果另一个事务也生成了一个订单，也是购买了01的商品，把库存数量改为0了。两个事务互相干扰了。如何防止干扰呢? 其实就是加锁，当一个事务操作01号商品，其他事务不能进行操作。

- 持久性(durability)：一个事务一旦提交，它对数据库中数据的改变就应该是永久性的。

### MySQL 事务处理

MySQL中只有使用 Innodb 数据引擎的数据库或表才**支持事务**。MySQL默认以自动提交(autocommit)模式运行。

使用show engines；--查看服务器支持的引擎。

```bash
BEGIN(START TRANSACTION) - 显示开启一个事务
COMMIT 提交事务，并使已对数据库进行的所有修改变为永久性的
ROLLBACK 回滚事务，并撤销正在进行的所有未提交的修改.

begin;
update product
set stock = 90
where id='10001';
commit;
//ROLLBACK;
```

#### 事务并发问题

多个事务交互执行，这个过程中间就会出现问题。

- 脏读
- 不可重复读
- 幻读

#### MySQL事务隔离级别

| 事务隔离级别               | 脏读 | 不可重复读 | 幻读 |
| -------------------------- | ---- | ---------- | ---- |
| 读未提交(read-uncommitted) | 是   | 是         | 是   |
| 读已提交(read-committed)   | 否   | 是         | 是   |
| 可重复读(repeatable-read)  | 否   | 否         | 是   |
| 串行化(serializable)       | 否   | 否         | 否   |





### JDBC 事务处理

- Connection接口

- - JDBC事务处理是基于Connection的，JDBC通过Connection对象进行事务管理；
  - JDBC默认事务处理行为是自动提交；

- 事务相关方法

- - setAutoCommit 设置自动提交；
  - commit 提交事务；
  - rollback 回滚事务；



```java
public void addOrder() {
    Connection connection = null;
    Statement statement = null;
    try {
        Class.forName(driver);// 加载驱动
        connection = DriverManager.getConnection(url， name， password);
        connection.setAutoCommit(false);// 手动提交事务
        statement = connection.createStatement();
        statement.execute("insert into `order` values('10002'，'10001'，2，2499，now()，null，null，'王某'，'110'，'北京王府井'，'待发货')");
        statement.execute("update product set stock = stock-2 where id='10001'");
        connection.commit();
    } catch (Exception e) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        e.printStackTrace();
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```



JDBC 事务的隔离级别

- `TRANSACTION_NONE`( 不支持事务) ；
- `TRANSACTION_READ_UNCOMMITTED`；
- `TRANSACTION_READ_COMMITTED`；
- `TRANSACTION_REPEATABLE_READ`；
- `TRANSACTION_SERIALIZABLE`；

事务隔离级别设置

- `getTransactionIsolation` 获取当前隔离级别；
- `setTransactionIsolation` 设置隔离级别；



### Spring事务处理

#### 编程式事务管理

通过 `TransactionTemplate`或者`TransactionManager`手动管理事务，实际应用中很少使用，但是对于你理解 Spring 事务管理原理有帮助。

使用`TransactionTemplate` 进行编程式事务管理的示例代码如下：

```java
@Autowired
private TransactionTemplate transactionTemplate;
public void testTransaction() {
    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
        @Override
        protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
            try {

                // ....  业务代码
            } catch (Exception e){
                // 回滚
                transactionStatus.setRollbackOnly();
            }

        }
    });
}
```

使用 `TransactionManager` 进行编程式事务管理的示例代码如下：

```java
@Autowired
private PlatformTransactionManager transactionManager;

public void testTransaction() {

  TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
          try {
               // ....  业务代码
              transactionManager.commit(status);
          } catch (Exception e) {
              transactionManager.rollback(status);
          }
}
```

#### 声明式事务管理

推荐使用（代码侵入性最小），实际是通过 AOP 实现（基于`@Transactional` 的全注解方式使用最多）。

使用 `@Transactional`注解进行事务管理的示例代码如下：

```java
@Transactional(propagation=propagation.PROPAGATION_REQUIRED)
public void aMethod {
  //do something
  B b = new B();
  C c = new C();
  b.bMethod();
  c.cMethod();
}
```



Spring 事务传播行为：

- PROPAGATION_REQUIRED：支持当前事务，如果当前没有事务，就新建一个事务；
- PROPAGATION_SUPPORTS：支持当前事务，如果当前没有事务.就以非事务方式执行；
- PROPAGATION_MANDATORY：支持当前事务，如果当前没有事务，就抛出异常；
- PROPAGATION_REQUIRES_NEW：新建事务，如果当前存在事务，把当前事务挂起；
- PROPAGATION_NOT_SUPPORTED：以非事务方式执行操作，如果当前存在事务，就把当前事务挂起；
- PROPAGETION_NEVER：以非事务方式执行，如果当前事务存在，则抛出异常；
- PROPAGATION_NESTED：如果当前存在事务，则在嵌套事务内执行，如果当前没有事务，就新建一个事务。





期待与你一起进步😋

![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN/static/img/202108311552149.png)
