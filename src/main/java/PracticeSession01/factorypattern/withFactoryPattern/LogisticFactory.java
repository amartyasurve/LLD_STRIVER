package PracticeSession01.factorypattern.withFactoryPattern;

import java.util.Objects;

public class LogisticFactory {
    //this class will only deal with creation of objects depending upon the class we want to implement
    public static Logistics getInstance(String mode) {
        if (Objects.equals(mode, "Air")) {
            return new AirLogistics();
        } else if (Objects.equals(mode, "Road")) {
            return new RoadLogistics();
        }
        else if (mode=="Train"){
            return new TrainLogistics();
        }
        return null;
    }

}
