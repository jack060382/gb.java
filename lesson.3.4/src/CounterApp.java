import java.lang.ref.Reference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CounterApp {

    private static class Counter {
        private int value;

        public Counter() {
            this.value = 0;
        }

        public synchronized void increment() {
            value ++ ;
        }

        public int getValue() {
            return value;
        }
    }

    private static class DoubleCounter {

        private Object lock1 = new Object();
        private Object lock2 = new Object();

        private int value1;
        private int value2;

        public DoubleCounter() {
            this.value1 = 0;
            this.value2 = 0;
        }

        public void increment1() {
            synchronized (lock1) {
                value1 ++ ;
            }
        }
        public void increment2() {
            synchronized (lock2) {
                value2 ++ ;
            }
        }

        @Override
        public String toString() {
            return "value1="+value1+" value2="+value2;
        }

    }


    public static void main(String[] args) throws  InterruptedException{

        DoubleCounter DoubleCounter = new DoubleCounter();
        Counter counter = new Counter();

        AtomicInteger aInt = new AtomicInteger(0);
        //AtomicReference<String> aCnt = new AtomicReference<String>("test");


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i < 10_000; i++) {

                    aInt.incrementAndGet();
                    counter.increment();

                    DoubleCounter.increment1();
                    if (i % 2 == 0) {
                        DoubleCounter.increment2();
                    }
/*
                    //атомарно и синхронно увеличить два счётчика
                    synchronized (DoubleCounter) {
                        DoubleCounter.increment1();
                        DoubleCounter.increment2();
                    }

 */

                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(aInt.get());
        System.out.println(counter.getValue());
        System.out.println(DoubleCounter.toString());
    }


}
