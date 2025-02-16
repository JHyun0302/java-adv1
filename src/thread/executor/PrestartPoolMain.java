package thread.executor;

import static thread.executor.ExecutorUtils.printState;
import static util.ThreadUtils.sleep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 스레드를 미리 생성해두면, 처음 요청에서 사용되는 스레드의 생성 시간을 줄일 수 있다.
 * `ThreadPoolExecutor.prestartAllCoreThreads()` 를 사용하면 기본 스레드를 미리 생성할 수 있다.
 */
public class PrestartPoolMain {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1000);
        printState(es);
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) es;
        poolExecutor.prestartAllCoreThreads();
        sleep(100);
        printState(es);
    }
}
