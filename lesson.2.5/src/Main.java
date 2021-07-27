public class Main {
    public static void main(String[] args) throws InterruptedException {
        //System.out.println("Lesson 2.5");
        //doMainThreadDemo();
        //doThreadSleepDemo();
        doRaceConditionDemoTwo();
    }

    static void doRaceConditionDemoTwo() throws InterruptedException {

        ThreadSafeTank tank = new ThreadSafeTank();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(tank.get());
                try {
                    Thread.sleep((long) Math.random() * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(tank.get());
                try {
                    Thread.sleep((long) Math.random() * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(tank.get());
                try {
                    Thread.sleep((long) Math.random() * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

        System.out.println("end");
    }

    static void doThreadSafeCounterDemo() throws InterruptedException {
        ThreadSafe tank = new ThreadSafe();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                tank.increase();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                tank.increase();
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                tank.increase();
            }
        });

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("end "+tank.count);

    }


    static void doRaceConditionCounterDemo() throws InterruptedException {
        Counter tank = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                tank.increase();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                tank.increase();
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                tank.increase();
            }
        });

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("end "+tank.count);

    }

    static void doRaceConditionDemo() throws InterruptedException {

        FuelTank tank = new FuelTank();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(tank.get());
                try {
                    Thread.sleep((long) Math.random() * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(tank.get());
                try {
                    Thread.sleep((long) Math.random() * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(tank.get());
                try {
                    Thread.sleep((long) Math.random() * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

        System.out.println("end");
    }

    static void doThreadJoinDemo() throws InterruptedException {
        System.out.println("Start: "+Thread.currentThread().getName());

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            for (int i = 5; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        });
        t2.start();

        t1.join();
        t2.join();

        System.out.println("end");
    }

    static void doConcurrencyDemo() {

        System.out.println("Start: "+Thread.currentThread().getName());

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            for (int i = 5; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        });
        t2.start();

        System.out.println("end");
    }

    static void doThreadSleepDemo() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("END");
    }

    static void doMainThreadDemo() {
        System.out.println(Thread.currentThread().getId());
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getState());
    }

    static void doThreadStartDemoTwo () {
        Thread t1 = new Thread(new RunnableOne());
        t1.start();
        //t1.getState();
    }

    static void doThreadStartDemoOne() {
        Thread t1 = new Thread(new RunnableOne());
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable");
                for (int i = 0; i < 5; i++) {
                    System.out.println(i);
                }
            }
        });
        t2.start();

        Thread t3 = new Thread(() -> {
                System.out.println("Runnable");
                for (int i = 0; i < 5; i++) {
                    System.out.println(i);
                }
        });
        t3.start();

        // Так не делать! - нет смысла наследовать, так как тянем кучу ненужны свойств.
        Thread t4 = new ThreadOne();
        t4.start();
    }


}

class ThreadOne extends Thread {
    @Override
    public void run() {
        System.out.println("Runnable");
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
    }
}

class RunnableOne implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable");
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
    }
}