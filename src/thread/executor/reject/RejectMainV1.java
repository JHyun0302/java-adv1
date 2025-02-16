package thread.executor.reject;

import static util.MyLogger.log;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import thread.executor.RunnableTask;

/**
 * **AbortPolicy**: 새로운 작업을 제출할 때 `RejectedExecutionException` 을 발생시킨다. 기본 정책이다.
 */
public class RejectMainV1 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new AbortPolicy());

        executor.submit(new RunnableTask("taks1"));

        try {
            executor.submit(new RunnableTask("taks2"));
        } catch (RejectedExecutionException e) {
            log("요청 초과");
            // 포기, 다시 시도 등 다양한 고민을 하면 됨
            log(e);
        }

        executor.close();
    }
}
