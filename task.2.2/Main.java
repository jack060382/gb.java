package task10;

public class Main {

    public static void main(String[] args) {
        System.out.println("task10");

        String[][] array = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "u", "4"},
        };
        int sum = 0;
        try {
            ArrayCalculator arrayCalc = new ArrayCalculator();
            sum = arrayCalc.getArraySum(array);
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        if (sum > 0) {
            System.out.println("Array sum is " + sum);
        }

    }



}
