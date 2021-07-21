package ru.gb.exceptions;

public class BoartSystem {

    private static final float MIN_FUEL_SIZE = 2.5f;
    private static final int MIN_BATTERY_SIZE = 2;

    public void checkFuel(float size) throws LawFuelLevelException {
        if (size < MIN_FUEL_SIZE) {
            throw new LawFuelLevelException("Low level fuel detected: "+size+". Minimal is "+MIN_FUEL_SIZE);
        }
        System.out.println("Fuel ok!");
    }

    public void checkElectricity(int size) throws UnstableElectricityException {
        if (size < MIN_BATTERY_SIZE) {
            throw new UnstableElectricityException("Unstable electricity: "+size+". Minimal is "+MIN_BATTERY_SIZE);
        }
        System.out.println("Batteru ok!");
    }

}
