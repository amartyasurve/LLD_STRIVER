package abstractfactorypattern.WithAbstractFactory;

public class LondonPaymentFactory implements Country{
    @Override
    public PaymentGateway createInstance(String mode) {
       if(mode=="DebitPay"){
           return new DebitPay();
       }
       return null;
    }

    @Override
    public Invoice createInvoice(String mode) {
       if(mode=="GST"){
           return new GSTInvoice();
       }
        return null;
    }
}
