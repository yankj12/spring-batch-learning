# spring-batch-learning

spring batch的demo

## 待实现功能

从user表逐个查询出用户，向score表登记分数，并更新user表flag标志位

## 数据结构

### spring-batch的数据结构

`spring-batch-core`这个jar包中，`org.springframework.batch.core`包路径下`schema-mysql.sql`是spring-batch启动所需的基本数据结构，需要自行创建

### 自定义表的表结构

用户表

```SQL
CREATE TABLE
    t_user
    (
        id bigint NOT NULL AUTO_INCREMENT,
        userName VARCHAR(40),
        phone VARCHAR(40),
        email VARCHAR(100),
        validStatus VARCHAR(2),
        flag VARCHAR(2),
        insertTime DATETIME,
        updateTime DATETIME,
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

```

分数表

```SQL
CREATE TABLE
    t_score
    (
        id bigint NOT NULL AUTO_INCREMENT,
        user_id bigint,
        user_name VARCHAR(20),
        course_name VARCHAR(40),
        score INT,
        insertTime DATETIME,
        updateTime DATETIME,
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
```