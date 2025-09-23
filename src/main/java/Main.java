public class Main {
    public static void main(String[] args) {
        //concept
//        Analytics analytics1; // Just a reference
//        analytics1 = new Analytics(); // Now it points to an object
//        analytics1.analyze();         // Safe to call now


        //execution
//        Analytics analytics1=new Analytics();//in this way we cannot create the object as the constructor is private
//        Analytics analytics2=new Analytics();//same class has two different instances this not single ton
        Analytics analytics1=Analytics.getAnalytics();
        Analytics analytics2=Analytics.getAnalytics();
        Analytics analytics3=Analytics.getAnalytics();
        System.out.println(analytics1);
//        Analytics analytics4=new Analytics(Analytics.analytics);

        System.out.println(analytics2);
        System.out.println(analytics3);
        System.out.println(Analytics.getAnalytics());

        // this is  not a single ton pattern

    }
}