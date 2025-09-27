public class Main {
    public static void main(String[] args) {
        //concept
//        Analytics analytics1; // Just a reference
//        analytics1 = new Analytics(); // Now it points to an object
//        analytics1.analyze();         // Safe to call now


        //execution
//        Analytics analytics1=new Analytics();//in this way we cannot create the object as the constructor is private
//        Analytics analytics2=new Analytics();//same class has two different instances this not single ton
        Analytics analytics1=Analytics.getInstance();
        Analytics analytics2=Analytics.getInstance();
        Analytics analytics3=Analytics.getInstance();
        System.out.println(analytics1);
//        Analytics analytics4=new Analytics(Analytics.analytics);

        System.out.println(analytics1);
        System.out.println(analytics1);
        System.out.println(Analytics.getInstance());

        // this is  not a single ton pattern

    }
}