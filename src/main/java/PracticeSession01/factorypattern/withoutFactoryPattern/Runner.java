package PracticeSession01.factorypattern.withoutFactoryPattern;

public class Runner {
    public static void main(String[] args){
        /*
        * Logistic Service is intended to send the package
        * but in this case logistics service is creating the object
        * and then sending the package
        * this is violation of single responsibility principle
        * */
        LogisticService logisticService=new LogisticService();
        Logistics lgs = logisticService.getInstance("Air");
        lgs.send();
    }
}
