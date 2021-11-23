import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task 3.1");
        doTask1();
        doTask2();
        doTask3();
    }

    public static void doTask3() {
        System.out.println("Большая задача");
        /*
        a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
        b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
            поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
        c. Для хранения фруктов внутри коробки можете использовать ArrayList;
        d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов
            и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
        e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
            которую подадут в compare в качестве параметра, true - если их веса равны,
            false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
        f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
            (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
            соответственно в текущей коробке фруктов не остается,
            а в другую перекидываются объекты, которые были в этой коробке;
        g. Не забываем про метод добавления фрукта в коробку.
         */

        // Коробка 1
        Box box1 = new Box();
        try {
            box1.putFruit(new Apple());
            box1.putFruit(new Apple());
            box1.putFruit(new Apple());
            box1.putFruit(new Apple());
            box1.putFruit(new Orange());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(box1.getWeight());
        try {
            System.out.println(box1.takeFruit().toString());
            System.out.println(box1.takeFruit().toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(box1.getWeight());

        // Коробка 2
        Box box2 = new Box();
        try {
            box2.putFruit(new Apple());
            box2.putFruit(new Apple());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(box2.getWeight());

        // Коробка 3
        Box box3 = new Box();
        try {
            box3.putFruit(new Orange());
            box3.putFruit(new Orange());
            box3.putFruit(new Orange());
            box3.putFruit(new Orange());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(box3.getWeight());

        if (box1.compare(box2)) {
            System.out.println("Equals weights.");
        }
        else {
            System.out.println("None weights.");
        }

        if (box1.compare(box3)) {
            System.out.println("Equals weights.");
        }
        else {
            System.out.println("None weights.");
        }


        try {
            box1.shiftAllFruitsFrom(box2);
            System.out.println(box1.getWeight()+" "+box2.getWeight());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            box1.shiftAllFruitsFrom(box3);
            System.out.println(box1.getWeight()+" "+box2.getWeight());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void doTask2() {
        System.out.println("Написать метод, который преобразует массив в ArrayList;");
        String[] testArr = {
                "1111",
                "2222",
                "3333",
                "4444"
        };
        System.out.println(java.util.Arrays.toString(testArr));
        System.out.println(Arrays.toString(createArrayListFromArray(testArr).toArray()));

    }

    private static ArrayList<Object> createArrayListFromArray(Object[] arr) {
        ArrayList<Object> list = new ArrayList<Object>();
        for (int i=0; i<arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }


    private static void doTask1() {
        System.out.println("Написать метод, который меняет два элемента массива местами." +
                "(массив может быть любого ссылочного типа);"
        );

        String[] testArr = {
                "1111",
                "2222",
                "3333",
                "4444"
        };
        System.out.println(java.util.Arrays.toString(testArr));
        try {
            System.out.println(java.util.Arrays.toString(changeArrayElements(testArr, 0, 1)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static Object[] changeArrayElements(Object[] arr, int pos1, int pos2) throws Exception {

        if (pos1 < 0 || pos1 > arr.length) {
            throw new Exception("Incorrect pos 1");
        }
        if (pos2 < 0 || pos2 > arr.length) {
            throw new Exception("Incorrect pos 2");
        }

        Object tmp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = tmp;

        return arr;
    }
}
