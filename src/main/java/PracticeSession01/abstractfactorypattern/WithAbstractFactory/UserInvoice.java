package PracticeSession01.abstractfactorypattern.WithAbstractFactory;

public class UserInvoice implements Invoice {
    @Override
    public void generateInvoice() {
        System.out.println("user invoice generated");
    }



}
