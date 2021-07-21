package task5;

import task4.TicTacToe;

public class Main {
    public static void main(String[] args) {
        Worker[] workers = new Worker[5];
        workers[0] = new Worker(
                "Джеймс Тибериус Кирк",
                45,
                "Капитан командор",
                "+7 999 9999999",
                "kirk@gmail.com",
                130000.00f
        );
        workers[1] = new Worker(
                "Спок",
                44,
                "Командор",
                "+7 999 9999999",
                "spok@vulcan.vu",
                110000.00f
        );
        workers[2] = new Worker(
                "Леонард Боунс Маккой",
                39,
                "Лейтенант",
                "+7 999 9999999",
                "bones@enterprise.med",
                110000.00f
        );
        workers[3] = new Worker(
                "Монтгомери Скотт",
                38,
                "Лейтенант",
                "+7 999 9999999",
                "scotty@edinburg.en",
                110000.00f
        );
        workers[4] = new Worker(
                "Хикару Сулу",
                33,
                "Лейтенант",
                "+7 999 9999999",
                "sulu@mail.jp",
                110000.00f
        );

        for (Worker worker:workers) {
            if (worker.getAge() >= 40) {
                worker.print();
            }
        }
        
    }
}
