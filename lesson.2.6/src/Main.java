package ru.gb.current;

public class Main {

    public static void main(String[] args) {
        /** Lambda Method Reference */
        new Thread(Server::new).start();
        /** Basic lambda */
        new Thread(() -> new Client()).start();
    }

}
