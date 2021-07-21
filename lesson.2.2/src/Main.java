import ru.gb.exceptions.BoartSystem;
import ru.gb.exceptions.CheckSystemException;
import ru.gb.exceptions.LawFuelLevelException;
import ru.gb.exceptions.UnstableElectricityException;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        BoartSystem boardSystem = new BoartSystem();

        try {
            checkSystem(boardSystem);
        } catch (CheckSystemException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Check complited!");
            boardSystem = null;
        }
        System.out.println("Exit!");
    }

    static void doLockDemo() {
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            // Exception expected
        } finally {
            lock.unlock();
        }
    }

    static void checkSystem(BoartSystem boardSystem) {
        try {
            boardSystem.checkFuel(3.4f);
            boardSystem.checkElectricity(1);
        } catch (LawFuelLevelException | UnstableElectricityException ex) {
            throw new CheckSystemException("System check issue", ex);
            //System.out.println(ex.getCause());
            //System.out.println(ex.getMessage());
            //ex.printStackTrace();
        } catch (Exception ex) {
            throw new CheckSystemException("Unknown issue", ex);
        }
        System.out.println("Car state ok!");
    }

}