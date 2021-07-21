package lesson7;

public class Plate {
    private int food;
    public Plate(int food) {
        this.food = food;
    }

    public void increaseFood(int n) {
        food += n;
    }

    public boolean decreaseFood(int n) {
        if (food < n) {
            return false;
        }
        food -= n;
        return true;
    }
    public void info() {
        System.out.println("plate: " + food);
    }
}
