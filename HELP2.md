正向索引：通过唯一键/主键生成与对象的映射关系
倒排索引：也叫反向索引，它的设计是为了存储在全文搜索下某个单词在一个文档或一组文档中存储位置的映射，是在文档检索系统中最常用的数据结构
全量索引+增量索引
全量索引：检索系统再启动时一次性读取当前数据库中(不能从数据库中直接读取)的所有数据，建立索引
增量索引：系统运行过程中，监控数据库变化，即增量， 实时加载更新，构建索引

索引类型：
普通索引--基本索引类型，支持单列和多列
唯一索引--表示唯一的，不允许重复的索引，支持单列和多列，注意如果是多列共同构成唯一索引，代表的是多列的数据组合是唯一的
主键索引--主键是特殊的唯一索引，同样支持单列和多列，但是必须指定为PRIMARY KEY 注意每个表中只能有一个主键

InnoDB存储引擎使用B+树来构造索引，之所以使用B+树构造索引，是因为数据和索引都保存在磁盘中，为了提高性能，每次会把部分数据读入内存来计算，所以每次查找数据时把磁盘IO次数控制在一个很小
的数量级是最优的，最好是常数数量级。
B树：多路平衡查找树 B平衡的意思 Balance m阶(m>=2)的B树有以下特性
     -树中的每个节点最多有m个子节点
     -除了根节点和叶子节点子外，其他每个节点至少有m/2个子节点
     -所有的叶子节点都在同一层
     -节点中关键字的顺序按照升序排列
B+树：-非叶子节点不存储数据，只存储索引
     -叶子节点包含全部关键字信息，且叶子节点按照关键字顺序相互连接
     
 

spring事务的传播:总结就是在 事务控制的方法调用的时候，调用的是对象不是代理的对象，而是被代理的对象，所以没有事务控制

使用默认的事务处理方式
spring的事务默认是对RuntimeException进行回滚，而不继承RuntimeException的不回滚。因为在java的设计中，它认为不继承RuntimeException的异常是”checkException”或普通异常，如IOException，
这些异常在java语法中是要求强制处理的。对于这些普通异常，spring默认它们都已经处理，所以默认不回滚。可以添加rollbackfor=Exception.class来表示所有的Exception都回滚。

内部调用
不带事务的方法调用该类中带事务的方法，不会回滚。因为spring的回滚是用过代理模式生成的，如果是一个不带事务的方法调用该类的带事务的方法，
直接通过this.xxx()调用，而不生成代理事务，所以事务不起作用。常见解决方法，拆类。


例如在 a方法中，调用 b 但是 a 方法没有事务控制，这时 b 方法 的事物控制也会失效，这时什么原因?

/**
 * 调用child , 由于child 抛出异常，查看事物回滚
 */
@Transactional(propagation = Propagation.SUPPORTS ,rollbackFor = Exception.class)
public Integer parent() {
    try {
        child();
    }catch (Exception e){

    }
    Course course = new Course();
    course.setName("childCourse");
    course.setCreateTime(new Date());
    testDao .insert(course);
    return Integer.MAX_VALUE;
}
/**
 * 被parent 调用，该事物传播机制为新启一个事物，关于事物传播，请另查询资料
 * 插入一条course 记录
 */
@Transactional(propagation = Propagation.REQUIRES_NEW ,rollbackFor = Exception.class)
public Integer child() {
    Course course = new Course();
    course.setName("childCourse");
    course.setCreateTime(new Date());
    testDao .insert(course);
    // 抛出异常
    int i = 10 / 0 ;
    return Integer.MAX_VALUE;
}
在child方法中声明事务传播--REQUIRES_NEW
因此child在执行的时候应该挂起parent方法的事务，等执行完毕child方法的事务之后，唤醒parent的事务，这种情况的预期结果是parent插入成功，child插入失败，但是结果却是全部成功？？？？？？

分析接口动态代理

public interface TestService {
    public Integer parent();
    public Integer child();
    public Integer test1();
    public Integer test2();
}

------------------

@Service
public class TestServiceImpl implements TestService {
   @Autowired
   private TestDao testDao ;
    
   private Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
    /**
    * 调用child , 由于child 抛出异常，查看事物回滚
    */
    @Transactional(propagation = Propagation.SUPPORTS ,rollbackFor = Exception.class)
    public Integer parent() {
        try {
            child();
        }catch (Exception e){
    
        }
        Course course = new Course();
        course.setName("parent");
        course.setCreateTime(new Date());
        testDao .insert(course);
        return Integer.MAX_VALUE;
    }
    /**
    * 被parent 调用，该事物传播机制为新启一个事物，关于事物传播，请另查询资料
    * 插入一条course 记录
    */
    @Transactional(propagation = Propagation.REQUIRES_NEW ,rollbackFor = Exception.class)
    public Integer child() {
        Course course = new Course();
        course.setName("childCourse");
        course.setCreateTime(new Date());
        testDao .insert(course);
        // 抛出异常
        int i = 10 / 0 ;
        return Integer.MAX_VALUE;
    }
    
    public Integer test1() {
        System.out.println("test1 被调用");
        return Integer.MAX_VALUE;
    }
    
    public Integer test2() {
        System.out.println("test2 被调用");
        return Integer.MAX_VALUE;
    }
}

public class MyInvocationHandler implements InvocationHandler {
    private Object target ;

    public MyInvocationHandler(Object target){
        this.target = target ;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().startsWith("test")){
            System.out.println("我被代理了");
        }
        Object invoke = method.invoke(target, args);
        return invoke;
    }
}



MyInvocationHandler invocationHandler = new MyInvocationHandler(new TestServiceImpl());
TestService testService =(TestService) Proxy.newProxyInstance(TestService.class.getClassLoader(),new TestServiceImpl().getClass().getInterfaces(),invocationHandler);
testService.test1();
testService.test2();
testService.abcTest();

这也就是spring 的事务控制大致实现。

然后 模拟 一下上面的实现，在test1 方法中，调用 test2 代码 修改如下

public Integer test1() {
    System.out.println("test1 被调用");
    test2();
    System.out.print("-------------------------------------------------------");
    return Integer.MAX_VALUE;
}

public Integer test2() {
    System.out.println("test2 被调用");
    System.out.println("-------------------------------------------------------");
    return Integer.MAX_VALUE;
}

public Integer abcTest() {
    System.out.println("abcTest 被调用");
    System.out.println("-------------------------------------------------------");
    return null;
}


发现问题了吧，在test1 中调用的test2 没有执行的时候没有被代理。所以直接输出的test2 被调用，而直接调用的test2 被代理对象拦截了。其实看代码，可以得出


在test1 中执行的test2（）方法 其实真正的写法是 this.test2（）;然后看MyInvocationHandler 实现

public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if(method.getName().startsWith("test")){
        System.out.println("我被代理了");
    }
    Object invoke = method.invoke(target, args);
    return invoke;
}

在这里,执行的test2 的对象其实不是代理对象，而是被代理对象。同样的在parent（） 中调用child（） child（）的执行的调用对象，也不是代理对象，而是被代理对象，所以child 没有事务控制。
具体的可以debugger 查看。这难道是jdk 动态代理给spring 留下的坑？呵呵！！

解决方案---------->
在配置文件中 添加如下代码

<aop:aspectj-autoproxy expose-proxy="true" />
然后在parent 中,调用child使用的是代理的对象而不是被代理的对象


@Transactional(propagation = Propagation.SUPPORTS ,rollbackFor = Exception.class)
public Integer parent() {
    try {
        ((TestService) AopContext.currentProxy()). child();   //调用的对象是代理的对象     直接调用child()  其实是this.child() 是被代理的对象没有事务控制
    }catch (Exception e){

    }
    Course course = new Course();
    course.setName("parent");
    course.setCreateTime(new Date());
    testDao .insert(course);
    return Integer.MAX_VALUE;
}



