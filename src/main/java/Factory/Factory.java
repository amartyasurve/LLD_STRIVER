package Factory;

import java.util.Objects;

interface Logistics {
    public void send();
}

class AirLogistics implements Logistics {
    @Override
    public void send() {
        System.out.println("send via air");
    }
}

class RoadLogistics implements Logistics {
    @Override
    public void send() {
        System.out.println("send via Road");
    }
}

class LogisticsFactory{
    public static Logistics getLogistics(String mode){
        if(Objects.equals(mode, "air")){
            return new AirLogistics();
        }
        else{
            return new RoadLogistics();
        }
    }
}



class LogisticService {
    public void send(String mode){
        Logistics logistics=LogisticsFactory.getLogistics(mode);
        logistics.send();
    }
}


public class Factory {
    public static void main(String[] args) {
        LogisticService logisticService = new LogisticService();
        logisticService.send("air");
    }
}
