import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dk
 * @date 2016/3/8
 */
public class CustomThreadPoolExecutor {
    private ThreadPoolExecutor pool = null;

    public void init(){
        pool = new ThreadPoolExecutor(10,
                30,
                30L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new CustomThreadFactory(),
                new CustomRejectedExecutionHandler());

    }

    public void destory(){
        if(pool != null){
            pool.shutdown();
        }
    }

    public ExecutorService getCustomThreadPoolExecutor(){
        return this.pool;
    }

    private class CustomThreadFactory implements ThreadFactory{
        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread();
            String threadName = CustomThreadPoolExecutor.class.getSimpleName()+count.addAndGet(1);
            System.out.println(threadName);
            t.setName(threadName);
            return t;
        }
    }

    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("error .....");
        }
    }

    public static void main(String[] args) {
        CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor();
        executor.init();

        ExecutorService pool = executor.getCustomThreadPoolExecutor();
        for (int i = 0; i <100 ; i++) {
            System.out.println("提交第" + i + "个任务!");
            pool.execute(new Runnable() {
                @Override
                public void run() {
//                    try {
//                        Thread.sleep(300);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    System.out.println("running=====");

                }
            });
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
