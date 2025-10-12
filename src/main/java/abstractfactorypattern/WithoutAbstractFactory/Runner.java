package abstractfactorypattern.WithoutAbstractFactory;

public class Runner {
    public static void main(String[] args){
        CheckoutService service=new CheckoutService();
        service.doCheckout("UPI","GST");
    }
}
