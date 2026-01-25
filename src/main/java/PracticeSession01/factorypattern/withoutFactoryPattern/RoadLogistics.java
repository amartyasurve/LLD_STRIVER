package PracticeSession01.factorypattern.withoutFactoryPattern;

public class RoadLogistics implements Logistics {
    @Override
    public void send(){
        System.out.println("this is an implementation of Road logistics");
    }
}

