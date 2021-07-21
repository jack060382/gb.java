package task9;

public class Wall implements Obstacle {

    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean overcome(Entity entity) {
        return entity.jump(height);
    };

}
