future模式
    举个例子：比如去吃早点时，点了包子和凉菜，包子需要等3分钟，凉菜只需1分钟，如果是串行的一个执行，在吃上早点的时候需要等待4分钟，
         但是因为你在等包子的时候，可以同时准备凉菜，所以在准备凉菜的过程中，可以同时准备包子，这样只需要等待3分钟。那Future这种模式就是后面这种执行模式。

join()方法：
    程序在main线程中调用t1线程的join方法，则main线程放弃cpu控制权，并返回t1线程继续执行直到线程t1执行完毕
         所以结果是t1线程执行完后，才到主线程执行，相当于在main线程中同步t1线程，t1执行完了，main线程才有执行的机会
         
join(0)不代表等待0秒, 而是无限等待直至B线程执行完毕，A才执行
    在A线程中调用了B线程的join()方法时，表示只有当B线程执行完毕时，A线程才能继续执行。注意，这里调用的join方法是没有传参的，
         join方法其实也可以传递一个参数给它的
      
采用future模式示例：   
public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		
		// 等凉菜 
		Callable ca1 = new Callable(){
 
			@Override
			public String call() throws Exception {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "凉菜准备完毕";
			}
		};
		FutureTask<String> ft1 = new FutureTask<String>(ca1);
		new Thread(ft1).start();
		
		// 等包子 -- 必须要等待返回的结果，所以要调用join方法
		Callable ca2 = new Callable(){
 
				@Override
				public Object call() throws Exception {
					try {
						Thread.sleep(1000*3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return "包子准备完毕";
			}
		};
		FutureTask<String> ft2 = new FutureTask<String>(ca2);
		new Thread(ft2).start();
		
		System.out.println(ft1.get());
		System.out.println(ft2.get());
		
		long end = System.currentTimeMillis();
		System.out.println("准备完毕时间："+(end-start));
}
