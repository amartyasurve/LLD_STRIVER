package factorypattern.withoutFactoryPattern;

import java.util.Objects;

public class LogisticService {
    public Logistics getInstance(String mode) {
        if (Objects.equals(mode, "Air")) {
            return new AirLogistics();
        } else if (Objects.equals(mode, "Road")) {
            return new RoadLogistics();
        }
        return null;
    }
}
