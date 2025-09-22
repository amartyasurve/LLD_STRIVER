public class Main {
    public static void main(String[] args) {
        Analytics analytics1 = new Analytics();//two different object of the same class
        analytics1.countRun();

        Analytics analytics2 = new Analytics();
        analytics2.countRun();

        System.out.println(analytics1);
        System.out.println(analytics2);
    }
}