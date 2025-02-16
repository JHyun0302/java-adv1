package thread.executor.reject;

import static util.MyLogger.log;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import thread.executor.RunnableTask;

/**
 * **DiscardPolicy**: 새로운 작업을 조용히 버린다.
 */
public class RejectMainV2 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardPolicy());

        executor.submit(new RunnableTask("taks1"));
        executor.submit(new RunnableTask("taks2"));
        executor.submit(new RunnableTask("taks3"));

        executor.close();
    }
}
