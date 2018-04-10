##Redis配置
###依赖配置
- org.springframework.boot:spring-boot-starter-data-redis
- org.springframework.session:spring-session-data-redis:1.3.1.RELEASE

###配置类
- 新建配置类RedisClusterConfig.java
- 添加@Configuration和@EnableRedisHttpSession注解

###单机配置
- spring.redis.host = localhost
- spring.redis.port = 6379
###集群配置
- spring.redis.cluster.nodes=10.3.254.55:6379,10.3.254.55:6380
- 多个地址之间使用逗号隔开
 
##Elasticsearch配置
###加载依赖
- org.elasticsearch.client:transport:${elaSearchVersion}
- org.apache.logging.log4j:log4j-to-slf4j:${log4j2slf4jVersion}

###添加配置类
- com.dyenigma.twinsapi.config.ElasticsearchConfigure.java

###配置文件
- spring.data.elasticsearch.cluster-nodes = 10.3.254.53:9300;10.3.254.54:9300;10.3.254.55:9300
- spring.data.elasticsearchlocal = false
- spring.data.elasticsearch.properties.transport.tcp.connect_timeout = 60s

##MongoDB配置
###加载依赖
- org.springframework.boot:spring-boot-starter-data-mongodb

###添加配置类
- com.dyenigma.twinsapi.config.MasterMongoConfigure.java
- com.dyenigma.twinsapi.config.SlaveMongoConfigure.java

###配置文件，多数据源
- spring.data.mongodb.master.host = 10.3.50.221,10.3.50.221,10.3.50.221
- spring.data.mongodb.master.port = 27221
- spring.data.mongodb.master.database = slave
- spring.data.mongodb.master.username = bigdata
- spring.data.mongodb.master.password = bigdata&2018
- spring.data.mongodb.slave.host = 10.3.50.221
- spring.data.mongodb.slave.port = 27221
- spring.data.mongodb.slave.database = slave
- spring.data.mongodb.slave.username = bigdata
- spring.data.mongodb.slave.password = bigdata&2018

##Druid数据源配置
###加载依赖
- com.alibaba:druid-spring-boot-starter:${druidVersion}

###DruidDataSource配置属性列表
- url：连接数据库的url
- username：连接数据库的用户名
- password：连接数据库的密码
- driverClassName：根据url自动识别
- initialSize：默认值0，初始化时建立物理连接的个数。
- maxActive：最大连接池数量
- minIdle：最小连接池数量
- maxWait：获取连接时最大等待时间，单位毫秒
- poolPreparedStatements：默认值false，是否缓存preparedStatement
- maxPoolPreparedStatementPerConnectionSize：默认值-1，要启用PSCache，必须配置大于0
- validationQuery：用来检测连接是否有效的sql，常用select 'x'
- validationQueryTimeout：单位：秒，检测连接是否有效的超时时间
- testOnBorrow：默认值true，申请连接时执行检测连接是否有效，做了这个配置会降低性能
- testOnReturn：默认值false，归还连接时执行检测连接是否有效，做了这个配置会降低性能
- testWhileIdle：默认值false，建议配置为true，不影响性能，并且保证安全性
- keepAlive：默认值false
- timeBetweenEvictionRunsMillis：默认1分钟
- minEvictableIdleTimeMillis：连接保持空闲而不被驱逐的最小时间
- connectionInitSqls：物理连接初始化的时候执行的sql
- exceptionSorter：当数据库抛出一些不可恢复的异常时，抛弃连接
- filters：属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat，日志用的filter:log4j，防御sql注入的filter:wall
- proxyFilters：Filter集合，如果同时配置了filters和proxyFilters，是组合关系，并非替换关系

###WebStatFilter配置
-  enabled：是否开启该配置
-  url-pattern：配置基本监控路径
-  exclusions：经常需要排除一些不必要的url
-  session-stat-enable：session统计功能开关
-  session-stat-max-count：缺省sessionStatMaxCount是1000个
-  profile-enable：监控单个url调用的sql列表
-  principalSessionName：配置当前的session用户
-  principalCookieName：配置当前的user

###StatViewServlet配置
- enabled：默认true
- url-pattern：内置监控页面访问路径
- reset-enable：清零功能开关
- login-username：监控页面的访问账号
- login-password：监控页面的访问密码
- allow：IP白名单，多个使用逗号隔开，优先级低
- deny：IP黑名单，多个使用逗号隔开，优先级高

###多数据源配置


###密码加密