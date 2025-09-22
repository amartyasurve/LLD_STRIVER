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
    private static final Analytics analytics = new Analytics();

    private Analytics(){}

    public static Analytics getAnalytics(){
        return analytics;
    }

}
