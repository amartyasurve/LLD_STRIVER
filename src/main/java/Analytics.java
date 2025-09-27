public class Analytics {
    //    private int run = 0;
//    private int submit = 0;
//
//    public Analytics() {}
//
//    public void countRun() {
//        run++;
//    }
//
//    public void countSubmit() {
//        submit++;
//    }
//
//    public int getSubmit() {
//        return submit;
//    }
//
//    public int getRun() {
//        return run;
//    }

    //Non-static field 'analytics' cannot be referenced from a static context
    //static field

    //incorrect
   /*
   private Analytics analytics = new Analytics();

    public Analytics(){}

    public static Analytics getAnalytics(){
       return analytics;
    }
    */

    //correct
    /*
    private static Analytics analytics = new Analytics();

    public Analytics(){}

    public static Analytics getAnalytics(){
        return analytics;
    }
*/
    //correct
    private static volatile Analytics analytics;

    private Analytics() {
    }

    public static Analytics getInstance() {
        if (analytics == null) {
            synchronized (Analytics.class) {
                if (analytics == null) {
                    analytics=new Analytics();
                }
            }
        }
        return analytics;
    }


}
