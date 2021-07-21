package task2;

public class Main {
    public static void main(String[] args) {
        System.out.println(doTask1(10,1));
        doTask2(-1);
        System.out.println(doTask3(-10));
        doTask4("Stroka", 5);
        System.out.println(doTask5( 2000));
    }

    /**
     * задача 5
     * @param year
     * @return
     */
    private static boolean doTask5(int year) {
        return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
    }

    /**
     * задача 4
     * @param a
     * @return
     */
    private static void doTask4(String str, int a) {
        for (int i=0; i<a; i++) {
            System.out.println(str);
        }
    }


    /**
     * задача 3
     * @param a
     * @return
     */
    private static boolean doTask3(int a) {
        return a < 0;
    }

    /**
     * задача 2
     * @param a
     * @return
     */
    private static void doTask2(int a) {
        System.out.println("Число " + (a >= 0 ? "положительное" : "отрицательное"));
    }

    /**
     * задача 1
     * @param a
     * @param b
     * @return
     */
    private static boolean doTask1(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <=20;
    }
}
