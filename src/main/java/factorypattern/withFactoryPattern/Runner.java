package factorypattern.withFactoryPattern;

import factorypattern.withFactoryPattern.LogisticService;

public class Runner {
    public static void main(String[] args){
        /*
        * i Have one imaginary package
        * */
        LogisticService logisticService=new LogisticService();
        logisticService.send("Road");
    }
}
