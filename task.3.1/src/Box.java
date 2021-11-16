import java.util.ArrayList;

public class Box {
    private float weight = 0.0f;
    private String type = "";
    private ArrayList<Fruit> fruits = new ArrayList<Fruit>();

    public Fruit takeFruit() throws Exception {

        int index = fruits.size()-1;

        if (index < 0) {
            throw new Exception("Ooops! Cheburashka was here.");
        }

        Fruit fruit = fruits.get(index);
        fruits.remove(index);
        weight -= fruit.getWeight();

        if (index == 0) {
            clear();
        }
        return fruit;
    }

    public void shiftAllFruitsFrom(Box otherBox) throws Exception {

        if (otherBox.getType() == "") {
            throw new Exception("Are you drunk?");
        }

        if (type != otherBox.getType()) {
            throw new Exception("Whong box! Take box with "+type);
        }

        float otherWeight = otherBox.getWeight();
        while (otherWeight > 0.00001f) {
            Fruit fruit = otherBox.takeFruit();
            putFruit(fruit);
            otherWeight = otherBox.getWeight();
        }
    }

    public boolean compare(Box otherBox) {
        if (Math.abs(otherBox.getWeight() - weight) < 0.0001f) {
            return true;
        }
        return false;
    }

    private void clear() {
        fruits.clear();
        type = "";
        weight = 0.0f;
    }

    public boolean putFruit(Fruit fruit) throws Exception {

        if (
                type != ""
                && type != fruit.getType()
        ) {
            throw new Exception("Wrong box! Here we can keeping only "+type);
        }

        fruits.add(fruit);
        type = fruit.getType();
        weight += fruit.getWeight();

        return true;
    }

    public float getWeight() {
        return weight;
    }
    public String getType() {
        return type;
    }
}
