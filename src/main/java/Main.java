import singleton.lazyloading.billpugh.Analytics;

public class Main {
    public static void main(String[] args) {
        //concept
//        singleton.lazyloading.lazyloading.Analytics analytics1; // Just a reference
//        analytics1 = new singleton.lazyloading.lazyloading.Analytics(); // Now it points to an object
//        analytics1.analyze();         // Safe to call now


        //execution
//        singleton.lazyloading.lazyloading.Analytics analytics1=new singleton.lazyloading.lazyloading.Analytics();//in this way we cannot create the object as the constructor is private
//        singleton.lazyloading.lazyloading.Analytics analytics2=new singleton.lazyloading.lazyloading.Analytics();//same class has two different instances this not single ton
        Analytics analytics1=Analytics.getInstance();
        Analytics analytics2=Analytics.getInstance();
        Analytics analytics3=Analytics.getInstance();
        System.out.println(analytics1);
//        singleton.lazyloading.lazyloading.Analytics analytics4=new singleton.lazyloading.lazyloading.Analytics(singleton.lazyloading.lazyloading.Analytics.analytics);

        System.out.println(analytics1);
        System.out.println(analytics1);
        System.out.println(Analytics.getInstance());

        // this is  not a single ton pattern

    }
}