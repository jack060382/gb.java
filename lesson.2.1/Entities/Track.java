package lesson1.Entities;

public class Track implements Obstacle {

    private int distance;

    public Track(int distance) {
        this.distance = distance;
    }

    @Override
    public void overcome(Entity entity) {
        //entity.run(distance);
    }

    ;
}