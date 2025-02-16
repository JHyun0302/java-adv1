package thread.executor.reject;

import static util.MyLogger.log;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import thread.executor.RunnableTask;

/**
 * 사용자 정의(**RejectedExecutionHandler**)**: 사용자는 `RejectedExecutionHandler` 인터페이스를 구현하여 자신만의 거절 처리 전략을 정의할 수 있다.
 * 이를 통해 특정 요구사항에 맞는 작업 거절 방식을 설정 가능
 */
public class RejectMainV4 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new MyRejectedExecutionHandler());

        executor.submit(new RunnableTask("taks1"));
        executor.submit(new RunnableTask("taks2"));
        executor.submit(new RunnableTask("task3"));
        executor.submit(new RunnableTask("task4"));

        executor.close();
    }

    static class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        static AtomicInteger count = new AtomicInteger();

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            int i = count.incrementAndGet();
            log("[경고] 거절된 누적 작업 수: " + i);
        }
    }
}
