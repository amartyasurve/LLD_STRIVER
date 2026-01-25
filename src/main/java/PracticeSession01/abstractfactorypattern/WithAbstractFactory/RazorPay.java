package PracticeSession01.abstractfactorypattern.WithAbstractFactory;

public class RazorPay implements PaymentGateway {
    @Override
    public void makePayement() {
        System.out.println("payment done through razor pay");
    }
}
