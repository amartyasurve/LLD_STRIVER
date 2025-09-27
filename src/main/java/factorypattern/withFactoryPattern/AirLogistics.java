package factorypattern.withFactoryPattern;

public class AirLogistics implements Logistics{
    @Override
    public void send(){
        System.out.println("this is an implementation of air logistics");
    }
}
