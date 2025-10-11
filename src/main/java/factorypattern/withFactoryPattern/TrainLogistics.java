package factorypattern.withFactoryPattern;

public class TrainLogistics implements Logistics{
    @Override
    public void send(){
        System.out.println("send via Train Logistics");
    }
}
