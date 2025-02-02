package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/**
 * synchronized
 * 모든 객체(인스턴스)는 내부에 자신만의 락(`lock`)을 가지고 있다.
 * 모니터 락(monitor lock)이라도고 부른다.
 * 스레드가 `synchronized` 키워드가 있는 메서드에 진입하려면 반드시 해당 인스턴스의 락이 있어야 한다!
 */
public class BankAccountV2 implements BankAccount {
    private int balance;
//    volatile private int balance;

    public BankAccountV2(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public synchronized boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());

        // ==임계 영역 시작==
        log("[검증 시작] 출금액: " + amount + ", 잔액: " + balance);
        if (balance < amount) {
            log("[검증 실패]");
            return false;
        }

        // 잔고가 출금액 보다 많으면, 진행
        log("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);
        sleep(1000); //출금에 걸리는 시간으로 가정
        balance -= amount;
        log("[출금 완료] 출금액: " + amount + ", 잔액: " + balance);
        // ==임계 영역 종료==

        log("거래 종료");
        return false;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}
