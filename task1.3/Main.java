package task3;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //doTask1();
        //doTask2();
        //doTask3();
        //doTask4();

        /*
        int[] array= doTask5(10, 55);
        printArray(array);
        */

        //doTask6();

        /*
        int[] array = getRandomArray(5, 10);
        printArray(array);
        boolean result = doTask7(array);
        System.out.println(result);
        */

        int[] array = {0,1,2,3,4,5,6,7,8,9};

        printArray(array);
        array = doTask8(array, 3);
        printArray(array);
        System.out.println("\n");
        printArray(array);
        array = doTask8(array, -3);
        printArray(array);
    }


    /**
     * *** Написать метод, которому на вход подается одномерный массив и число n
     * (может быть положительным, или отрицательным),
     * при этом метод должен сместить все элементы массива на n позиций.
     * Элементы смещаются циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
     * Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ];
     * [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
     * При каком n в какую сторону сдвиг можете выбирать сами.
     */
    private static int[] doTask8(int[] array, int n) {
        for (int i = 1; i <= Math.abs(n); i++) {
            if (n > 0) {
                array = doArrayStepRight(array);
            }
            else {
                array = doArrayStepLeft(array);
            }
        }

        return array;
    }

    private static int[] doArrayStepLeft(int[] array) {
        int i = array.length-1;
        int tmp = 0;
        tmp = array[0];
        int t = 0, t1;
        for (int j = array.length-1; j >= 0; j--) {
            if (j == i+1) {
                t = array[j];
                array[j] = array[i];
            }
            else {
                t1 = array[j];
                array[j] = t;
                t = t1;
            }
        }
        array[i] = tmp;

        return array;
    }

    private static int[] doArrayStepRight(int[] array) {
        int i = 0;
        int tmp = 0;
        tmp = array[array.length-1];
        int t = 0, t1;
        for (int j = i+1; j < array.length; j++) {
            if (j == i+1) {
                t = array[j];
                array[j] = array[i];
            }
            else {
                t1 = array[j];
                array[j] = t;
                t = t1;
            }
        }
        array[i] = tmp;

        return array;
    }


    /**
     *7. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
     * метод должен вернуть true, если в массиве есть место,
     * в котором сумма левой и правой части массива равны.
     */
    private static boolean doTask7(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int left_sum = 0;
            int right_sum = 0;
            for (int j = 0; j < i; j++) {
                left_sum += array[j];
            }
            for (int j = i; j < array.length; j++) {
                right_sum += array[j];
            }
            System.out.println(left_sum+" "+right_sum);
            if (left_sum == right_sum) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create and fill random int[]
     * @param max
     * @param length
     * @return
     */
    private static int[] getRandomArray(int max, int length) {
        Random random = new Random();
        int[] array = {};
        for (int i = 0; i < length; i++) {
            array = Arrays.copyOf(array, i+1);
            array[i] = random.nextInt(max);
        }
        return array;
    }

    /**
     * 6. * Задать одномерный массив и найти в нем минимальный и максимальный элементы ;
     */
    private static void doTask6() {
        int[] array = getRandomArray(100, 10);

        printArray(array);

        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        System.out.println("Max value: "+max);
    }


    /**
     * 5. Написать метод, принимающий на вход два аргумента: len и initialValue,
     * и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue;
     */
    private static int[] doTask5(int len, int initialValue) {
        int[] array = {};
        for (int i = 0; i < len; i++) {
            array = Arrays.copyOf(array, i+1);
            array[i] = 55;
        }
        return array;
    }


    /**
     * 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
     * и с помощью цикла(-ов) заполнить его диагональные элементы единицами
     * (можно только одну из диагоналей, если обе сложно).
     * Определить элементы одной из диагоналей можно по следующему принципу:
     * индексы таких элементов равны, то есть [0][0], [1][1], [2][2], …, [n][n];
     */
    private static void doTask4() {
        int[][] array = {
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };
        printArray(array);

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == j) {
                    array[i][j] = 1;
                }
                if (i + j + 1 == array.length) {
                    array[i][j] = 1;
                }
            }
        }

        printArray(array);
    }

    /**
     * 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
     * пройти по нему циклом, и числа меньшие 6 умножить на 2;
     */
    private static void doTask3() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        printArray(array);

        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }

        printArray(array);

    }

    /**
     * 2. Задать пустой целочисленный массив длиной 100.
     * С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100;
     */
    private static void doTask2() {
        int[] array = {};

        printArray(array);

        for (int i = 1; i <= 100; i++) {
            array = Arrays.copyOf(array, i);
            array[i-1] = i;
        }

        printArray(array);

    }

    /**
     * 1. Задать целочисленный массив, состоящий из элементов 0 и 1.
     * Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
     * С помощью цикла и условия заменить 0 на 1, 1 на 0;
     */
    private static void doTask1() {
        int[] ar_bit = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        printArray(ar_bit);

        for (int i = 0; i < ar_bit.length; i++) {
            if (ar_bit[i] == 1) {
                ar_bit[i] = 0;
            }
            else {
                ar_bit[i] = 1;
            }
        }

        printArray(ar_bit);
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
            if (i != 0 && i % 20 == 0) {
                System.out.println("...");
            }
        }
        System.out.println();
    }

    private static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
