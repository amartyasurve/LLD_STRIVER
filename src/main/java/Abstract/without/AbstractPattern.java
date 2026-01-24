package Abstract.without;

/*
* Problem Statement : We want to implement checkout service --> payment + invoice
* INDIA --> payment (razorpay or payu) + invoice (gstinvoice)
* USA --> payment (paypal or stripe) + invoice (usinvoic)
*
* checkoutService should only deal with processing payment and creating invoice and they should not
* know which underline payment method is used or invoice is used
*
* */


import abstractfactorypattern.WithAbstractFactory.PaymentGateway;

import java.util.Objects;

interface PaymentGatewayI{
    public void processPayment();
}

class RazorPay implements PaymentGatewayI{
    @Override
    public void processPayment(){
        System.out.println("payment via razorpay");
    }
}

class PayU implements PaymentGatewayI{
    @Override
    public void processPayment(){
        System.out.println("payment via PayU");
    }
}

class PayPal implements PaymentGatewayI{
    @Override
    public void processPayment(){
        System.out.println("payment via PayPal");
    }
}

class Stripe implements PaymentGatewayI{
    @Override
    public void processPayment(){
        System.out.println("payment via Stripe");
    }
}

interface InvoiceI{
    public void generateInvoice();
}

class GSTInvoice implements InvoiceI{
    @Override
    public void generateInvoice(){
        System.out.println("created invoice via GST");
    }
}

class USInvoice implements InvoiceI{
    @Override
    public void generateInvoice(){
        System.out.println("created invoice via USInvoice");
    }
}

/*
* now the underline configuration is done
* now comes the main part
* creation of Factories --> gets the underline objects that are needed
* */

//class PaymentGatewayFactory{
//    public static PaymentGatewayI getPaymentGatewayInstance(String mode){
//        if(Objects.equals(mode, "razorpay")){
//            return new RazorPay();
//        }
//        else if(Objects.equals(mode, "payu")){
//            return new PayU();
//        }
//        if(Objects.equals(mode, "paypal")){
//            return new PayPal();
//        }
//        else {
//            return new Stripe();
//        }
//    }
//}

//class InvoiceFactory{
//    public static InvoiceI getInvoiceInstance(String mode){
//        if(Objects.equals(mode, "gst")){
//            return new GSTInvoice();
//        }
//        else {
//            return new USInvoice();
//        }
//    }
//}

/*
* now we need a region specific getInstance
* */

interface RegionFactory {
    PaymentGatewayI createPaymentGateway(String gatewayType);
    InvoiceI createinvoiceGateway();

}

class India implements RegionFactory{
    @Override
    public PaymentGatewayI createPaymentGateway(String gatewayType){
        switch (gatewayType.toLowerCase()){
            case "razorpay":
                return new RazorPay();
            case "payu":
                return new PayU();
            default:
                throw new IllegalArgumentException("invalid gateway info : " + gatewayType);
        }
    }
    @Override
    public InvoiceI createinvoiceGateway(){
        return new GSTInvoice();
    }
}

class US implements RegionFactory{
    @Override
    public PaymentGatewayI createPaymentGateway(String gatewayType){
        switch (gatewayType.toLowerCase()){
            case "paypal":
                return new PayPal();
            case "stripe":
                return new Stripe();
            default:
                throw new IllegalArgumentException("invalid gateway info : " + gatewayType);
        }
    }
    @Override
    public InvoiceI createinvoiceGateway(){
        return new USInvoice();
    }
}

class CheckoutService{
    PaymentGatewayI paymentGatewayI;
    InvoiceI invoiceI;
    String gateway;
    public CheckoutService(RegionFactory Factory,String gateway){
        this.gateway=gateway;
        this.paymentGatewayI=Factory.createPaymentGateway(gateway);
        this.invoiceI=Factory.createinvoiceGateway();
    }
    public void doCheckout(){
     paymentGatewayI.processPayment();
     invoiceI.generateInvoice();
    }
}

public class AbstractPattern {
    public static void main(String[] args){
        CheckoutService checkoutService=new CheckoutService(new India(),"payU");
        checkoutService.doCheckout();
    }
}
