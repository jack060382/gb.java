public class Tunnel extends Stage {

    private final int capacity;
    private volatile int carsCount = 0;

    public Tunnel(int capacity) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.capacity = capacity;
        System.out.println("Tunnel capacity  = "+capacity);
    }

    @Override
    public void go(Car c) {

        if (carsCount >= capacity) {
            System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
            while (carsCount >= capacity) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(c.getName() + " начал этап: " + description);
        carsCount ++ ;
        try {
            Thread.sleep(length / c.getSpeed() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        carsCount -- ;
        System.out.println(c.getName() + " закончил этап: " + description);

    }
}