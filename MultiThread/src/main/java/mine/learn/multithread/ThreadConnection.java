package mine.learn.multithread;

public class ThreadConnection {
    public static void main(String[] args) {
        Account1 account1 = new Account1("建行卡", 0);
        new DepositThread("存钱者1", account1, 1800).start();
        new DepositThread("存钱者2", account1, 1800).start();
        new DrawThread("取钱者1", account1, 800).start();
        new DrawThread("取钱者2", account1, 800).start();
        new DrawThread("取钱者3", account1, 800).start();

    }
}

class Account1 {
    String accountNum;
    int money;
    // 标识账户中是否有存款
    boolean flag = false;

    public Account1(String accountNum, int money) {
        this.accountNum = accountNum;
        this.money = money;
    }

    // 取钱
    public synchronized void draw(int drawMoney) {
        // 如果没有钱则阻塞
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            money -= drawMoney;
            System.out.println(Thread.currentThread().getName() + "取走了" + drawMoney + "元");
            System.out.println("账户剩余" + money + "元");
            // 取走了钱将标识设为false，并通知存钱
            if (money <= 0)
                flag = false;
            notifyAll();
        }

    }

    // 存钱
    public synchronized void deposit(int depositMoney) {
        // 如果账户有钱则让存钱方法阻塞
        if (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            money += depositMoney;
            System.out.println(Thread.currentThread().getName() + "存入了" + depositMoney + "元");
            // 存入了钱将标识设置为true，并通知取钱
            flag = true;
            notifyAll();
        }
    }
}

class DrawThread extends Thread {
    Account1 account1;
    int drawMoney;

    public DrawThread(String name, Account1 account11, int drawMoney) {
        super(name);
        this.account1 = account11;
        this.drawMoney = drawMoney;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account1.draw(drawMoney);
        }
    }
}

class DepositThread extends Thread {
    Account1 account1;
    int depositMoney;

    public DepositThread(String name, Account1 account11, int depositMoney) {
        super(name);
        this.account1 = account11;
        this.depositMoney = depositMoney;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account1.deposit(depositMoney);
        }
    }
}
