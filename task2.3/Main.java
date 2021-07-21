package task11;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("task11");
        // Задача 1
        doStringArrayTask();

        // Задача 2
        doPhoneBookDemo();
    }

    static void doPhoneBookDemo() {
        PhoneBook book = new PhoneBook();
        book.add("Иванов",      "+71111111111");
        book.add("Петров",      "+72222222222");
        book.add("Иванов",      "+73333333333");
        book.add("Петров",      "+74444444444");
        book.add("Сидоров",     "+75555555555");
        book.add("Иванов",      "+76666666666");
        book.add("Анисимов",    "+77777777777");
        book.add("Григорьев",   "+78888888888");
        book.add("Григорьев",   "+79999999999");
        book.add("Юрьев",       "+70000000000");

        System.out.println(book.get("Иванов"));
        System.out.println(book.get("Юрьев"));
        System.out.println(book.get("Петров"));
        System.out.println(book.get("Петровwerwerwerwer"));
    }

    static void doStringArrayTask() {
        String[] words = {
                "circle", "one", "two", "map",
                "void", "tell", "about", "circle",
                "void", "step", "one", "map",
                "map", "parts", "void", "parts",
                "kid", "phone", "parts", "icq",
        };
        HashMap<String, Integer> uniqueWords = new HashMap<>();

        for (String word : words) {
            if (!uniqueWords.containsKey(word)) {
                uniqueWords.put(word, 1);
            }
            else {
                uniqueWords.replace(word, uniqueWords.get(word) + 1);
            }
        }

        System.out.println("Words counts: ");
        for (Map.Entry<String, Integer> uniqueWord : uniqueWords.entrySet()) {
            System.out.println(uniqueWord.getKey() + ": " + uniqueWord.getValue());
        }

        System.out.println("\nUnique words: ");
        for (Map.Entry<String, Integer> uniqueWord : uniqueWords.entrySet()) {
            if (uniqueWord.getValue() == 1) {
                System.out.println(uniqueWord.getKey());
            }
        }

    }

}
