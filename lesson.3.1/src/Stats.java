public class Stats <T extends Number>{
    private T[] nums;

    public Stats(T... nums) {
        this.nums = nums;
    }

    public double avg() {
        double sum = 0.0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i].doubleValue();
        }
        return sum/nums.length;
    }

    public boolean isSameAvg(Stats<? extends Number> stats) {
        return Math.abs(this.avg() - stats.avg()) < 0.0001;
    }

}
