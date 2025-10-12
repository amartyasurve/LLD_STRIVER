package abstractfactorypattern.WithoutAbstractFactory;

public class RazorPay implements PaymentGateway{
    @Override
    public void makePayement() {
        System.out.println("payment done through razor pay");
    }
}
