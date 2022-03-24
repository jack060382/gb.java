import java.util.concurrent.CountDownLatch;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(CARS_COUNT);

        Race race = new Race(new Road(60), new Tunnel((int)Math.floor(CARS_COUNT/2)), new Road(40));
        race.setCarCount(CARS_COUNT);
        race.setStartFlag(cdl);
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 10 + (int) (Math.random() * 20));
        }
        CountDownLatch cdlFinish = new CountDownLatch(CARS_COUNT);
        race.setFinishtFlag(cdlFinish);

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
    }
}




