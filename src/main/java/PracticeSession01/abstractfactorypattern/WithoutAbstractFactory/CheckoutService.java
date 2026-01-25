package PracticeSession01.abstractfactorypattern.WithoutAbstractFactory;

public class CheckoutService {

    public void doCheckout(String mode,String bill){
        PaymentGateway payment=IndiaPaymentFactory.createInstance(mode);
        payment.makePayement();
        Invoice invoice=IndiaPaymentFactory.createInvoice(bill);
        invoice.generateInvoice();
    }


}
