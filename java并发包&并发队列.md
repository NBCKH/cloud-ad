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
    
线程池原理和锁

    
    