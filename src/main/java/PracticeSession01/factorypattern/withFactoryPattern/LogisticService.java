package PracticeSession01.factorypattern.withFactoryPattern;

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
            assert logistics != null;
            logistics.send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
