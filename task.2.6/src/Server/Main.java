package Server;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task 2.6 Работа с сетью. Сервер!");
        new Thread(Server::new).start();
    }
}
