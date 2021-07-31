package Client;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task 2.6 Работа с сетью. Клиент!");
        new Thread(() -> new Client()).start();
    }
}
