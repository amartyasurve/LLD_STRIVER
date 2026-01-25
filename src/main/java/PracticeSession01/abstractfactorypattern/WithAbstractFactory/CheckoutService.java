package PracticeSession01.abstractfactorypattern.WithAbstractFactory;

public class CheckoutService {

    public void doCheckout(String mode,String bill,String country){
        PaymentGateway payment= CountryFactor.createCountry(country).createInstance(mode);
        payment.makePayement();
        Invoice invoice= CountryFactor.createCountry(country).createInvoice(bill);
        invoice.generateInvoice();
    }


}
