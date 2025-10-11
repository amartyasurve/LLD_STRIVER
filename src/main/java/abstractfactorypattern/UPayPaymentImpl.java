package abstractfactorypattern;

public class UPayPaymentImpl implements ProcessPaymentInterface{
    @Override
    public void processPayment(){
        System.out.println("This payment is done through UPay");
    }
}