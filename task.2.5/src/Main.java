import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];

    public static void main(String[] args) {
        System.out.println("Task 2.5 Многопоточность");
        Arrays.fill(arr, 1);

        System.out.println("One thread iteration");
        oneThreadIteration();
        System.out.println("Two thread iteration");
        try {
            twoThreadIteration();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void twoThreadIteration() throws InterruptedException{

        long t1 = System.currentTimeMillis();

        float[] arr1 = new float[h];
        float[] arr2 = new float[h];
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = calcElement(arr1[i]);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < arr2.length; i++) {
                arr2[i] = calcElement(arr2[i]);
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

        printTime(t1, System.currentTimeMillis());
    }

    private static void oneThreadIteration() {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = calcElement(arr[i]);
        }
        printTime(t1, System.currentTimeMillis());
    }

    private static void printTime(long t1, long t2) {
        System.out.println("Total time: "+((t2-t1)*0.001)+" seconds");
    }

    private static float calcElement(float element) {
        return (float)(element * Math.sin(0.2f + element / 5) * Math.cos(0.2f + element / 5) * Math.cos(0.4f + element / 2));
    }
}