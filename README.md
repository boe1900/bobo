# learning project(学习项目)

#### 使用技术：

springmvc

velocity 模板引擎

dubbo rpc远程调用

zookeeper 服务发现注册

kisso 登录权限控制

mybatis plus 部分代码自动生成

mysql 数据库


#### 内容：权限管理系统

权限管理基本功能

单点登录(SSO)：暂时实现根域名相同不同子域名的单点登录，参考kisso

#### 测试部署
1，创建数据库，执行数据库脚本(bobo-upms-rpc-service项目的src\main\resources\upms.sql)，修改数据库相应的数据库配置。

2,本地安装zookeeper并启动，保持默认端口配置，也可自行修改，项目上的配置也需修改。

3，启动bobo-upms-rpc-service项目BoboUpmsRpcServiceApplication.java的main方法，即启动dubbo服务。

4，配置bobo-upms-admin项目sso.properties的相关配置，并配置相应的host。

sso.properties
```
sso.secretkey=Lg8V51188n0809i5l8
sso.cookie.name=uid
sso.cookie.domain=.test.cn
sso.login.url=http://upms.test.cn:1111/sso/login
```
host

```
127.0.0.1 upms.test.cn
```


5，jetty或者tomcat启动bobo-upms-admin项目，我启动的端口是1111。

6，访问http://upms.test.cn:1111即可。
