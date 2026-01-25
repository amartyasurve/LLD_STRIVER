package PracticeSession01.singleton.lazyloading.billpugh;

public class Analytics {
    private Analytics() {
    }
    private static class Holder {
        private static final Analytics analytics = new Analytics();
    }

    public static Analytics getInstance() {
        return Holder.analytics;
    }
}


