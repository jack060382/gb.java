public class Orange implements Fruit{
    private float weight = 1.5f;
    private String type = "orange";

    @Override
    public String getType() {
        return type;
    }

    @Override
    public float getWeight() {
        return weight;
    }
}
