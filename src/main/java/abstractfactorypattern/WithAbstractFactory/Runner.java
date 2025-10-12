package abstractfactorypattern.WithAbstractFactory;

public class Runner {
    public static void main(String[] args){
        CheckoutService service=new CheckoutService();
        service.doCheckout("CreditPay","UsaInvoice","USA");
    }
}
