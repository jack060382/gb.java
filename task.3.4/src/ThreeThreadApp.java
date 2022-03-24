public class ThreeThreadApp {

    private static final Object mon = new Object();
    private static volatile char currentLetter = 'A';

    public static void main(String[] args) {
        System.out.println("Task.3.4 Создать три потока, каждый из которых " +
                "выводит определенную букву (A, B и C) 5 раз " +
                "(порядок – ABСABСABС). " +
                "Используйте wait/notify/notifyAll.");


        Thread thread1 = new Thread(() -> {
            new WaitNotifyClass( 'A').printChar();
        });
        Thread thread2 = new Thread(() -> {
            new WaitNotifyClass( 'B').printChar();
        });
        Thread thread3 = new Thread(() -> {
            new WaitNotifyClass( 'C').printChar();
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }


    private static class WaitNotifyClass {
        private final char Letter;

        public WaitNotifyClass(char Letter) {
            this.Letter = Letter;
        }

        public void printChar() {
            //System.out.println("this.Letter = "+this.Letter);
            synchronized (mon) {
                try {
                    for (int i = 0; i < 10; i++) {
                        while (currentLetter != Letter) {
                            mon.wait();
                        }
                        System.out.print(Letter);
                        if (currentLetter == 'A') currentLetter = 'B';
                        else if (currentLetter == 'B') currentLetter = 'C';
                        else if (currentLetter == 'C') currentLetter = 'A';
                        mon.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }



}
