public class StatsApp {
    public static void main(String[] args) {
        Stats<Integer> intStats = new Stats<>(1,2,3,4,5,6);
        //Stats<Integer> doubleStats = new Stats<>(1,2,3,4,5,6, 8);
        Stats<Number> doubleStats = new Stats<>(1,2.1,3.0,4.0,5.1,6.0);

        if (intStats.isSameAvg(doubleStats)) {
            System.out.println("=");
        }
        else {
            System.out.println("!=");
        }

        Integer [] arr = new Integer[] {1,2,3,4};
        System.out.println(getAvg(arr));
    }

    public static <T extends Number> double getAvg(T[] array) {
        return 0;
    }
}
