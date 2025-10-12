package abstractfactorypattern.WithoutAbstractFactory;

public class GSTInvoice implements Invoice{
    @Override
    public void generateInvoice(){
        System.out.println("gst invoice generated");
    }
}
