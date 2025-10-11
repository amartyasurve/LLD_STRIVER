package abstractfactorypattern;

public class RazorPayPaymentImpl implements ProcessPaymentInterface{
    @Override
    public void processPayment(){
        System.out.println("This payment is done through razorPay");
    }
}
