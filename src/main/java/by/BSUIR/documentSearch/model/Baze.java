package by.BSUIR.documentSearch.model;

public class Baze{
    private static Integer numberOfDocInBase;
    private static Baze instance;

    public static void setNumberOfDocInBase(Integer numberOfDocInBase) {
        Baze.numberOfDocInBase = numberOfDocInBase;
    }

    public static Integer getNumberOfDocInBase() {
        return numberOfDocInBase;
    }

    private Baze(Integer numOfDocInBase){
        this.numberOfDocInBase = numOfDocInBase;
    }

    public static Baze getInstance(Integer numOfDocInBase){
        if(instance == null){
            instance = new Baze(numOfDocInBase);
        }
        return instance;
    }
}
