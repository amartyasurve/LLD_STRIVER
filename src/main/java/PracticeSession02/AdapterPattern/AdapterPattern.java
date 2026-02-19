package PracticeSession02.AdapterPattern;

/*
* Hello! It is great to have you here for the SDE 2 interview. I've reviewed your background,
*  and I'd like to dive straight into a Low-Level Design (LLD) coding problem.
For an SDE 2 role, I'm interested in how you write clean, maintainable code and how you apply
* design patterns to solve integration issues.

The Scenario: Payment Gateway Integration
Imagine we run an e-commerce platform. Currently, our system handles payments through a standard interface called PaymentProcessor. We currently have an implementation for PayPal.

However, the business wants to introduce Stripe as a new payment option to reduce transaction fees. We have downloaded the Stripe SDK, but its method signatures are completely different from our existing interface.

Here is the setup:

The Existing Interface:

Java
interface PaymentProcessor {
    void pay(double amount);
}
The New (Incompatible) Stripe SDK:

You cannot change this class (it's a 3rd party library).

It requires the amount and a currency string.

Java
class StripeApi {
    public void makeCharge(double amount, String currency) {
        System.out.println("Charged " + amount + " " + currency + " via Stripe.");
    }
}
Your Task
Please implement the Adapter Pattern to integrate StripeApi into our application so that we can use it interchangeably with our existing PaymentProcessor interface.

Requirements:

Create the Adapter class.

Demonstrate how the client (main code) would call this without knowing it is using Stripe.

You can use Java, C++, Python, or C# (whichever you are most comfortable with).

Go ahead and write the implementation.
*
*
* */

class StripeApi {
    public void makeCharge(double amount, String currency) {
        System.out.println("Charged " + amount + " " + currency + " via Stripe.");
    }
}

interface PaymentProcessor {
    void pay(double amount);
}

class Paypal implements PaymentProcessor{
    @Override
    public void pay(double amount){
        System.out.println("payment of rupees : "+amount + " is successfull with Paypal");
    }
}

class StripeAdapter{
    StripeApi stripeApi;
    StripeAdapter(){
        this.stripeApi=new StripeApi();
    }
}



public class AdapterPattern {
    public static void main (String[] args){
        System.out.println("*** adapter pattern ***");
    }
}
