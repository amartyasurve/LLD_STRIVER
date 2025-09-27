import factorypattern.withFactoryPattern.LogisticService;
import factorypattern.withoutFactoryPattern.Logistics;

public class Main {
    public static void main(String[] args) {
        LogisticService logisticService=new LogisticService();
        logisticService.send("Road");

        /*Without factory pattern*/
        /*
        * The problem  here is in logistic service
        * the purpose of logistic service is to send the logistic but instead it is dealing with creation
        * of object of either airlogistic or road logisctics
        * */
    }
}