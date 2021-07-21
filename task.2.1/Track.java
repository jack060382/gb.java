package task9;

public class Track implements Obstacle {

    private int distance;

    public Track(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean overcome(Entity entity) {
        return entity.run(distance);
    }


}