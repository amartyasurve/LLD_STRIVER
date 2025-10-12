package abstractfactorypattern.WithoutAbstractFactory;

import java.util.Objects;

public class IndiaPaymentFactory {
    public static PaymentGateway createInstance (String mode){
        if(Objects.equals(mode, "RazorPay")){
            return new RazorPay();
        }
        else if(Objects.equals(mode, "UPI")){
            return new UpiPayment();
        }
        return null;
    }

    public static Invoice createInvoice(String mode){
        if(Objects.equals(mode, "GST")){
            return new GSTInvoice();
        }
        else if(Objects.equals(mode, "user")){
            return new UserInvoice();
        }
        return null;
    }
}
