对象的深浅复制
    1.对象的深浅拷贝主要针对的是引用数据类型
    2.浅拷贝对对象引用地址进行拷贝，并没有开辟新的栈，拷贝后的结果是两个对象指向同一引用地址，修改其中一个对象的属性，则另一个对象的属性也会改变
    3.深拷贝则是开启一个新的栈，两个对象对应两个不同引用地址，修改一个对象的属性，不会改变另一个对象的属性

hashMap使用什么对象作为key
    使用对象作为key时候，get元素的时候执行了equals()方法和hashCode()方法
    new一个新的对象时，地址变了，不能保证hash值和equals结果还是一样，所以取不到对应的value
    如何解决：在取值和插入时默认调用hashCode()方法和equals方法，重写这两个方法
    
缓存穿透怎么解决
    缓存穿透是指查询一个数据库一定不存在的数据，
    正常的使用缓存流程大致是，数据查询先进行缓存查询，如果key不存在或者key已经过期，再对数据库进行查询，并把查询到的对象，放进缓存，如果数据库查询对象为空，则不放进缓存
    一种解决方案：如果从数据库查询的对象为空，也放入缓存，只是设定的缓存过期时间较短，比如设置60s
    二种解决方案：布隆过滤器，查询请求的key存不存在
    
缓存雪崩
    是指在某一时间段，缓存集中过期失效
    产生原因：比如在集中一段时间写文本，又同时多了一段时间失效，查询周期性全部落在数据库上
    解决方案：一般采用不同分类商品缓存不同周期，在同一分类中的商品，加上一个随机因子，尽可能分散缓存过期时间，热门类目的商品缓存时间长一些，冷门的缓存时间短一些
    
缓存击穿
    是指一个key非常热点，在不停的扛着大并发，大并发集中对一个点进行访问。当这个key在失效的瞬间，持续的大并发就穿破缓存，直接请求数据库，就像在一个屏障上凿开了一个洞
    解决方案：让这个爆款的商品永不过期
    

    
    
    
