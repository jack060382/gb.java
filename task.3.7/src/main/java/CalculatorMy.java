
public class CalculatorMy {

    public CalculatorMy() {

    }


    public int sum(int a, int b) {
        return a + b;
    }

    public int mul(int a, int b) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a * b;
    }

    public int div(int a, int b) {
        //if (b == 0) return 0;
        return a/b;
    }




}