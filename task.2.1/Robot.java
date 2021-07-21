package task9;

public class Robot implements Entity{

    private int run_max_distance;
    private int run_max_height;
    private String serial;

    public Robot(String serial, int run_max_distance, int run_max_height) {
        this.run_max_distance = run_max_distance;
        this.run_max_height = run_max_height;
        this.serial = serial;
    }

    @Override
    public boolean run(int distance) {
        System.out.printf("%s robot is runing on %d meters. %n", serial, distance);
        if (distance > run_max_distance) {
            System.out.println("Fail!");
            return false;
        }
        return true;
    };

    @Override
    public boolean jump(int height) {
        System.out.printf("%s robot is jumping on %d meters. %n", serial, height);
        if (height > run_max_height) {
            System.out.println("Fail!");
            return false;
        }
        return true;
    };


}
