public class Apple implements Fruit{
    private float weight = 1.0f;
    private String type = "apple";

    @Override
    public String getType() {
        return type;
    }

    @Override
    public float getWeight() {
        return weight;
    }
}
