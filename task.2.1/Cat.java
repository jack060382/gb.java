package task9;

public class Cat implements Entity{

    private int run_max_distance;
    private int run_max_height;
    private String nick;


    public Cat(String nick, int run_max_distance, int run_max_height) {
        this.run_max_distance = run_max_distance;
        this.run_max_height = run_max_height;
        this.nick = nick;
    }

    @Override
    public boolean run(int distance) {
        System.out.printf("%s cat is runing on %d meters. %n", nick, distance);
        if (distance > run_max_distance) {
            System.out.println("Fail!");
            return false;
        }
        return true;
    };

    @Override
    public boolean jump(int height) {
        System.out.printf("%s cat is jumping on %d meters. %n", nick, height);
        if (height > run_max_height) {
            System.out.println("Fail!");
            return false;
        }
        return true;
    };


}
