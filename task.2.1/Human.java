package task9;

public class Human implements Entity{

    private int run_max_distance;
    private int run_max_height;
    private String name;

    public Human(String name, int run_max_distance, int run_max_height) {
        this.run_max_distance = run_max_distance;
        this.run_max_height = run_max_height;
        this.name = name;
    }

    @Override
    public boolean run(int distance) {
        System.out.printf("%s man is runing on %d meters. %n", name, distance);
        if (distance > run_max_distance) {
            System.out.println("Fail!");
            return false;
        }
        return true;
    };

    @Override
    public boolean jump(int height) {
        System.out.printf("%s man is jumping on %d meters. %n", name, height);
        if (height > run_max_height) {
            System.out.println("Fail!");
            return false;
        }
        return true;
    };


}
