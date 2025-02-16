package thread.executor.poolsize;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import thread.executor.RunnableTask;

/**
 * 기본 스레드를 사용하지 않고, 60초 생존 주기를 가진 초과 스레드만 사용한다.
 * 초과 스레드의 수는 제한이 없다.
 * 큐에 작업을 저장하지 않는다. (`SynchronousQueue`)
 * 대신에 생산자의 요청을 스레드 풀의 소비자 스레드가 직접 받아서 바로 처리한다.
 * 모든 요청이 대기하지 않고 스레드가 바로바로 처리한다. 따라서 빠른 처리가 가능
 */
public class PoolSizeMainV3 {
    public static void main(String[] args) {
//        ExecutorService es = Executors.newCachedThreadPool();
        /** 같은 코드 */
        ExecutorService es = new ThreadPoolExecutor(2, Integer.MAX_VALUE,
                3, TimeUnit.SECONDS, new SynchronousQueue<>());

        log("pool 생성");
        printState(es);

        for (int i = 1; i <= 4; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es);
        }

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(es);

        sleep(3000);
        log("== maximumPoolSize 대기 시간 초과 ==");
        printState(es);



        es.close();
        log("== shutdown 완료 ==");
    }
}
