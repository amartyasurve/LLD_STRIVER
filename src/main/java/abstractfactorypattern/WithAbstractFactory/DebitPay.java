package abstractfactorypattern.WithAbstractFactory;

public class DebitPay implements PaymentGateway{

    @Override
    public void makePayement() {
        System.out.println("payment through debit card");
    }
}
