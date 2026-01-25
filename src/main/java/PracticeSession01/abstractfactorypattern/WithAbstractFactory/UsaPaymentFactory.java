package PracticeSession01.abstractfactorypattern.WithAbstractFactory;

import java.util.Objects;

public class UsaPaymentFactory implements Country {


    @Override
    public PaymentGateway createInstance(String mode){
        if(Objects.equals(mode, "CreditPay")){
            return new CreditPay();
        }
        else if(Objects.equals(mode, "DebitPay")){
            return new DebitPay();
        }
        return null;
    }
    @Override
    public Invoice createInvoice(String mode){
        if(Objects.equals(mode, "UsaInvoice")){
            return new UsaInvoice();
        }
        else if(Objects.equals(mode, "user")){
            return new UserInvoice();
        }
        return null;
    }

}