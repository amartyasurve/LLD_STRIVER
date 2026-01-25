package PracticeSession01.abstractfactorypattern.WithoutAbstractFactory;

public class UpiPayment implements PaymentGateway{

    @Override
    public void makePayement() {
        System.out.println("payment though upi payment");
    }
}
