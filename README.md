```
This project only use to demo and raise the issue for other open soucre. 
```
### 1.Kafka 2.7 Demo

#### 1.1 Kafka 2.7 (一) 核心概念 https://www.cnblogs.com/hlkawa/p/14634984.html




### 2. Set the transation (JPA with Redis)

https://github.com/showkawa/springdoc-test/blob/main/src/main/java/com/brian/demo/config/RedisConfig.java

```
    rediTemplate.setEnableTransactionSupport(true);

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) throws SQLException {
        return new DataSourceTransactionManager(dataSource);
    }        
```





































### 10. Online free source
#### 10.1 Free Redis 
https://redislabs.com/
#### 10.2 Free PostgreSQL
https://www.elephantsql.com/ 