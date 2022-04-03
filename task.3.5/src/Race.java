import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    private CountDownLatch startFlag;
    private CountDownLatch finishtFlag;
    private boolean RaceStarted = false;
    private boolean isWinner = false;
    private List<Object> finishers = Collections.synchronizedList(new ArrayList<>());

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }

    private int carCount;

    public CountDownLatch getFinishtFlag() {
        return finishtFlag;
    }

    public void setFinishtFlag(CountDownLatch finishtFlag) {
        this.finishtFlag = finishtFlag;
    }

    public CountDownLatch getStartFlag() {
        return startFlag;
    }

    public void setStartFlag(CountDownLatch startFlag) {
        this.startFlag = startFlag;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public void StartRace() {
        if (!RaceStarted) {
            RaceStarted = true;
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        }
    }

    public void carFinished(Car car) {
        System.out.println(car.getName()+" завершает маршрут гонки!");
        finishers.add(car.getName());
        if (!isWinner) {
            System.out.println(car.getName() + " WIN!");
            isWinner = true;
        }
    }

    public void finish() {
        if (RaceStarted) {
            if (finishers.size() == carCount) {
                RaceStarted = false;
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
                System.out.println(Arrays.toString(finishers.toArray()));
            }
        }
    }

}