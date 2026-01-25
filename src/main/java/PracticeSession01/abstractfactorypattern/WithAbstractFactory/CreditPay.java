package PracticeSession01.abstractfactorypattern.WithAbstractFactory;

public class CreditPay implements PaymentGateway{
    @Override
    public void makePayement() {
        System.out.println("payment done through credit pay");
    }
}
