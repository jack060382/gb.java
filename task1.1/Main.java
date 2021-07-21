package task1;

public class Main {

    public static void main(String[] args) {
        compareNumbers();
        printColor();
        checkSumSign();
        printThreeWords();
    }


    private static void compareNumbers() {
        int a = 10;
        int b = 5;

        if (a >= b) {
            System.out.println("a >= b");
        }
        else {
            System.out.println("a < b");
        }
    }


    private static void printColor() {
        int value = 100;

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <=100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }


    private static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }


    private static void checkSumSign() {
        int a = -10;
        int b = 5;

        int c = a + b;

        if (c >= 0) {
            System.out.println("Сумма положительная");
        }
        else {
            System.out.println("Сумма отрицательная");
        }
    }

}
