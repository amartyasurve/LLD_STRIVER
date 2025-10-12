package abstractfactorypattern.WithAbstractFactory;

public class GSTInvoice implements Invoice {
    @Override
    public void generateInvoice(){
        System.out.println("gst invoice generated");
    }
}
