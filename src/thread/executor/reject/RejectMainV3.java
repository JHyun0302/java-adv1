package thread.executor.reject;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import thread.executor.RunnableTask;

/**
 * **CallerRunsPolicy**: 새로운 작업을 제출한 스레드가 대신해서 직접 작업을 실행한다.
 * 이 정책의 특징은 생산자 스레드가 소비자 대신 일을 수행하는 것도 있지만, 생산자 스레드가 대신 일을 수행하는 덕분에 작업의 생산 자체가 느려진다는 점이다.
 * 덕분에 작업의 생산 속도가 너무 빠르다면, 생산 속도를 조절할 수 있다.
 */
public class RejectMainV3 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());

        executor.submit(new RunnableTask("taks1"));
        executor.submit(new RunnableTask("taks2")); //main thread 작업 진행
        executor.submit(new RunnableTask("task3"));
        executor.submit(new RunnableTask("task4"));

        executor.close();
    }
}
