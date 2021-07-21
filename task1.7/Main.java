package lesson7;

public class Main {
    public static void main(String[] args) {
        Plate plate = new Plate(50);
        plate.info();

        Cat[] cats = {
                new Cat("Laert", 20),
                new Cat("Snow", 15),
                new Cat("Nginx", 25),
                new Cat("Tai", 10),
                new Cat("Doctor", 20),
        };
        for (int i = 0; i < cats.length; i++) {
            cats[i].eat(plate);
            if (!cats[i].getFullness()) {
                System.out.printf("%s is hangry!", cats[i].getName());
                plate.increaseFood(40);
            }
            plate.info();
        }

    }
}
