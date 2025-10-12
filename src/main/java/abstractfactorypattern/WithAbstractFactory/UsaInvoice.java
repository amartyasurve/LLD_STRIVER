package abstractfactorypattern.WithAbstractFactory;

public class UsaInvoice implements Invoice{
    @Override
    public void generateInvoice() {
        System.out.println("usa invoice");
    }
}
