public class Main {

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            Thread.currentThread().getId();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            System.out.println("run 1");
        }
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("run 2");
        }
    }
    // psvm
    public static void main(String[] args) {
        System.out.println("Lesson 3.4");

        Thread thread1 = new Thread(new MyRunnable());
        thread1.start(); // запустит отдельный новый поток

        Thread thread2 = new MyThread();
        thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run 3");
            }
        });

        thread3.start();
        try{
            thread3.join(1000);
            // дождались
        } catch (InterruptedException e) {
            e.printStackTrace();
            // не дождались
        }

        Thread thread4 = new Thread(() -> {
            System.out.println("run 4");
        });
        thread4.start();

        Thread thread5 = new Thread(() -> System.out.println("run 5"));
        thread5.start();

    }




}
