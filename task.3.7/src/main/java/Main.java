public class Main {
    public static void main(String[] args) {

        try {
            MyTests.start("CalcTests");
            //MyTests.start(CalcTests.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
