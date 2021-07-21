import sun.awt.image.ImageWatched;
import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("lesson 2.3");
        //doTreeSetDemo();

    }

    static void doTreeSetDemo() {

        Set<User> users = new TreeSet<>();
        users.add(new User(1, "u1"));
        users.add(new User(2, "u2"));
        users.add(new User(3, "u3"));
        users.add(new User(4, "u4"));

        //doIteratorForColectionDemo(users);

        Set<Integer> digits = new TreeSet<>();

        digits.add(12);
        //digits.add(null);
        digits.add(12);
        digits.add(56);

        doIteratorForColectionDemo(digits);
    }

    static void doLinkedHashDemo() {
        Set<Integer> digits = new LinkedHashSet<>();

        digits.add(12);
        digits.add(null);
        digits.add(12);
        digits.add(56);

        doIteratorForColectionDemo(digits);
    }

    static void doHashSetDemo() {
        Set<Integer> digits = new HashSet<>();

        digits.add(12);
        digits.add(null);
        digits.add(12);
        digits.add(56);

        doIteratorForColectionDemo(digits);
    }

    static void doIteratorForColectionDemo(Collection<Integer> digits) {
        Iterator<Integer> iterator = digits.iterator();
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            System.out.println(value);
        }

        for (Integer digit : digits) {
            System.out.println(digit);
        }

        System.out.println(digits);
    }

    static void doALinkedListDemo() {
        // Двусвязанный список
        List<Integer> digits = new LinkedList<>();

        digits.add(12);
        digits.add(null);
        digits.add(1);
        digits.add(56);

        doIteratorListDemo(digits);
    }


    static void doArrayListDemo() {
        // Типичный динамический массив
        List<Integer> digits = new ArrayList<>();

        digits.add(12);
        digits.add(null);
        digits.add(1);
        digits.add(56);

        doIteratorListDemo(digits);
    }


    static void doIteratorListDemo (List<Integer> digits) {
        System.out.println(digits.get(2));

        Iterator<Integer> iterator = digits.iterator();
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            System.out.println(value);
        }

        for (Integer digit : digits) {
            System.out.println(digit);
        }

        System.out.println(digits);
    }

}
