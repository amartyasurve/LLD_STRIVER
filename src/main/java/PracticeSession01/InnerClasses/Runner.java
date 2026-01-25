package PracticeSession01.InnerClasses;

public class Runner {
    private static String message = "Hello from Outer class!";
    Runner(){
        System.out.println("outer constructor called"+this.getClass());
    }

    class Inner {
        private int num=10;
        Inner(){
            System.out.println("inner constructor called");
            System.out.println("coninner : "+ this.getClass());
        }
        public static void display() {
            System.out.println(message); // can access private members
        }
    }

    public void parentmethod() {
        System.out.println("parent method called");
    }

}

class Run {
    public static void main(String[] args) {
//        Runner outer = new Runner();
//    outer.parentmethod();
        Runner.Inner inner = new Runner().new Inner();
        System.out.println(inner);// create inner class instance
        inner.display();
    }
}
