package lesson1.Entities;

public class Robot implements Entity {

    @Override
    public void move() {
        System.out.println("Robot is mooving");
    }

    @Override
    public void recharge() {
        System.out.println("Robot is rechargeing");
    }

}
