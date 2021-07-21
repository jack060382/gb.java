package lesson7;

public class Cat {
    private String name;
    private int appetite;
    private boolean fullness = false;


    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plate p) {

        if (fullness) {
            System.out.println("Mmmm I`m not hangry!");
            return;
        }

        if (!p.decreaseFood(appetite)) {
            System.out.println("I need more food! Myaaaau!");
        }
        else {
            System.out.println("Ooooh, Yesss!");
            fullness = true;
        }
    }

    public String getName() {
        return name;
    }

    public boolean getFullness() {
        return fullness;
    }
}
