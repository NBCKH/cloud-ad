vector与arrayList区别   
    都是通过数组来实现的，查询速度比较快，增加、修改、删除速度慢
    区别：线程安全问题
    vector 线程安全的 add方法用到synchronized修饰 效率不高
    arrayList 线程不安全 没用用到任何lock锁
    
hashTable和hashMap源码分析
    都是通过链表+数组来实现的 可以看一下hashMap源码
    区别：线程安全问题
    hashTable: 源码put方法是通过
    hashMap:

什么是线程安全问题
    保证在多个线程之间共享同个全局变量或者静态变量，保证数据一致性和原子性
线程同步有哪些方式
    synchronized lock
线程安全的类
    concurrentHashMap:分段锁计算  16段  将一个整体拆分成多个小的hashTable,每个线程使用不同的锁，默认分成16段
    concurrentHashMap内部使用segment来表示这些不同的部分，每个段其实就是一个小的hashTable,它们有自己的锁，只要多个修改操作发生在不同的段上它们可以并发进行。
        
    countDownLatch: 技术器
    
线程池原理和锁:http://www.ideabuffer.cn/2017/04/04/%E6%B7%B1%E5%85%A5%E7%90%86%E8%A7%A3Java%E7%BA%BF%E7%A8%8B%E6%B1%A0%EF%BC%9AThreadPoolExecutor/#addWorker%E6%96%B9%E6%B3%95
    什么时候用到线程池：
        需要处理的任务数量大
        单个任务处理时间比较短
    线程池的好处
        降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
        提高响应速度。当任务到达时，任务可以不需要的等到线程创建就能立即执行。
        提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。
    ThreadPoolExecutor:分析一下这个类内部对于线程的创建, 管理以及后台任务的调度等方面的执行原理。