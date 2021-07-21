package lesson1.Entities;

public class Human  implements Entity {

    @Override
    public void move() {
        System.out.println("Human is mooving");
    }

    @Override
    public void recharge() {
        System.out.println("Human is rechargeing");
    }

}
