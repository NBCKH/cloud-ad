dubbo是什么 https://blog.csdn.net/qq_43260557/article/details/84520175#2dubbo_5
    基于java的高性能rpc分布式服务框架
技术选型为什么要用到dubbo
    内部使用netty,zookeeper确保高性能高可用性
    使用dubbo可以将核心业务抽取出来，作为独立的服务，逐渐形成稳定的服务中心，提高业务灵活扩展
dubbo的协议推荐使用哪种？
    推荐使用nio复用单一长连接，并使用线程池并发处理请求，减少握手和加大并发效率，性能较好
dubbo需要web容器么
    不需要，如果硬要用的话，只会增加复杂性
dubbo内置了几种服务容器
    1.spring container
    2.jetty container
    3.log4j container  dubbo的服务容器只是一个简单的main方法，并加载一个简单的spring容器，用于暴露服务
dubbo里面有哪几种节点角色
    provider 暴露服务的服务提供方
    consumer 调用远程服务的服务消费方
    registry 服务注册与发现的注册中心
    monitor 统计服务的调用次数和调用时间的监控中心
    container 服务运行容器
    
    
调用关系