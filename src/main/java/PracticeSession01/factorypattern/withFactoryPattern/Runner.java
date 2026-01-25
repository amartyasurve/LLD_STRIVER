package PracticeSession01.factorypattern.withFactoryPattern;

public class Runner {
    public static void main(String[] args){
        /*
        * i Have one imaginary package
        * */
        LogisticService logisticService=new LogisticService();
        logisticService.send("Train");
    }
}
