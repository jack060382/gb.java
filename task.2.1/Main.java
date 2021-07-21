package task9;

public class Main {

    public static void main(String[] args) {
        Entity[] players = {
                new Cat("Begemot", 30, 2),
                new Human("Petrovich", 50, 3),
                new Robot("T3PO", 10, 1),
        };

        Obstacle[] stadium = {
                new Track(5),
                new Track(10),
                new Wall(1),
                new Track(15),
                new Wall(3),
        };

        for (Entity player : players) {
            boolean isWin = true;
            for (Obstacle obstacle : stadium) {
                if (!obstacle.overcome(player)) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) {
                System.out.println("Winner!");
            }
        }

    }

}
