package abstractfactorypattern.WithAbstractFactory;

import java.util.Objects;

public class IndiaPayment implements Country {


    @Override
    public PaymentGateway createInstance(String mode){
        if(Objects.equals(mode, "RazorPay")){
            return new RazorPay();
        }
        else if(Objects.equals(mode, "UPI")){
            return new UpiPayment();
        }
        return null;
    }
    @Override
    public Invoice createInvoice(String mode){
        if(Objects.equals(mode, "GST")){
            return new GSTInvoice();
        }
        else if(Objects.equals(mode, "user")){
            return new UserInvoice();
        }
        return null;
    }

}
