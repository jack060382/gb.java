public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится, скорость ["+speed+"]");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            race.getStartFlag().countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            race.getStartFlag().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        race.StartRace();

        try {
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            race.carFinished(this);
            race.getFinishtFlag().countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            race.getFinishtFlag().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        race.finish();
    }
}