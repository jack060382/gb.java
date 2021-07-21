package lesson1.Entities;

public class Wall implements Obstacle{

    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void overcome(Entity entity) {
        //entity.jump(height);
    };

}
