package task10;

public class ArrayCalculator {

    int MAX_ARRAY_SIZE = 4;

    public int getArraySum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;

        if (array.length > MAX_ARRAY_SIZE) {
            throw new MyArraySizeException("Incorrect array size: "+array.length+" rows. Max is "+MAX_ARRAY_SIZE);
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].length > MAX_ARRAY_SIZE) {
                throw new MyArraySizeException("Incorrect array size: "+array[i].length+" elements in line "+(i+1)+". Max is "+MAX_ARRAY_SIZE);
            }
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Incorrect element on position ["+(i+1)+"]["+(j+1)+"]");
                }
            }
        }

        return sum;
    }
}
