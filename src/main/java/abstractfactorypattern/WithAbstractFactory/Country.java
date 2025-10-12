package abstractfactorypattern.WithAbstractFactory;

public interface Country {
    public PaymentGateway createInstance(String mode);
    public Invoice createInvoice(String mode);
}
