package PracticeSession02.BuilderPattern;

class Order{
    //compulsory fields
    /*
    * why did we use private final keyword
    * private ensures the scope is withing the class itself
    * final we cannot immutate the variable once set
    * */

    private final String orderId;
    private final String customerId;

    //optional
    private final String shippingAddress;
    private final String billingAddress;
    private final String discountCode;
    private final boolean giftWrap;
    private final boolean priorityDelivery;
    private final String deliveryInstructions;

    /*
    * private constuctor for the order is there due to : creation of object order should be
    * happen only through builder method
    * */
    private Order(OrderBuilder orderBuilder){
        this.orderId= orderBuilder.orderId;
        this.customerId=orderBuilder.customerId;
        this.shippingAddress=orderBuilder.shippingAddress;
        this.billingAddress=orderBuilder.billingAddress;
        this.discountCode= orderBuilder.discountCode;
        this.giftWrap= orderBuilder.giftWrap;
        this.priorityDelivery= orderBuilder.priorityDelivery;
        this.deliveryInstructions= orderBuilder.deliveryInstructions;
    }

    public static class OrderBuilder{
        private String orderId;
        private String customerId;

        //optional
        private String shippingAddress;
        private String billingAddress;
        private String discountCode;
        private boolean giftWrap;
        private boolean priorityDelivery;
        private String deliveryInstructions;

        public OrderBuilder(String orderId,String customerId){
            this.orderId=orderId;
            this.customerId=customerId;
        }

        public OrderBuilder withShippingAddress(String shippingAddress){
            this.shippingAddress=shippingAddress;
            return this;
        }
        public OrderBuilder withBillingAddress(String billingAddress){
            this.billingAddress=billingAddress;
            return this;
        }
        public OrderBuilder withDiscountCode(String discountCode){
            this.discountCode=discountCode;
            return this;
        }
        public OrderBuilder withGiftWrap(boolean giftWrap){
            this.giftWrap=giftWrap;
            return this;
        }
        public OrderBuilder withPriorityDelivery(boolean priorityDelivery){
            this.priorityDelivery=priorityDelivery;
            return this;
        }

        public OrderBuilder withDeliveryInstruction(String deliveryInstructions){
            this.deliveryInstructions=deliveryInstructions;
            return this;
        }

        public Order build(){
            if(customerId==null || orderId==null){
                throw new IllegalArgumentException("customerId or orderId cannot be null");
            }
            if(priorityDelivery && shippingAddress==null){
                throw new IllegalArgumentException("for priority order shipping address is compulsory");
            }
            return new Order(this);
        }
    }

    @Override
    public String toString(){
        return "customerId : "+ customerId +" orderId : "+ orderId + " shipping Address : "+shippingAddress;
    }

}



/*
* Client Code
* */
public class BuilderPattern {
    public static void main(String[] args){
      Order order1=new Order.OrderBuilder("123","452").withShippingAddress("Pune").build();
      Order order2=new Order.OrderBuilder("565","456").withShippingAddress("Mumbai").build();
      Order order3=new Order.OrderBuilder("867","457").withPriorityDelivery(true).withShippingAddress("latur").build();
      Order order4=new Order.OrderBuilder("902","458").withShippingAddress("London").build();
//      Order order2=new Order(new Order.OrderBuilder("9009","342"));
        System.out.println(order1.toString());
        System.out.println(order2.toString());
        System.out.println(order3.toString());
        System.out.println(order4.toString());
    }
}
