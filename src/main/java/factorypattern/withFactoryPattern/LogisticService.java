package factorypattern.withFactoryPattern;

import java.util.Objects;

public class LogisticService {

    public void send(String mode) {
        /*
        //without static keyword
        LogisticFactory logisticFactory = new LogisticFactory();
        Logistics logistics = logisticFactory.getInstance(mode);
        logistics.send();
        */
        //with static keyword
        Logistics logistics = LogisticFactory.getInstance(mode);
        try {
            logistics.send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
