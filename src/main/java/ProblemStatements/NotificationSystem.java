package ProblemStatements;


import java.util.ArrayList;
import java.util.List;

enum OrderStatus {
    PLACED,
    SHIPPED,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED
}

interface NotificationInterface {
    public void sendNotification(String message);
}

class EmailNotification implements NotificationInterface {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sent Notification Via email " + message);
    }
}

class SMSNotification implements NotificationInterface {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sent Notification Via SMS" + message);
    }
}

class WhatsAppNotification implements NotificationInterface {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sent Notification Via WhatsApp" + message);
    }
}

class Customer {
    int customerId;
    String name;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }
}

class Item {
    int itemId;
    boolean isAvailable;
    String name;

    public Item(int itemId, String name, boolean isAvailable) {
        this.itemId = itemId;
        this.isAvailable = isAvailable;
        this.name = name;
    }
}

class Cart {
    ArrayList<Item> listOfItemsUserSpecific;
    Customer customer;

    public Cart(Customer customer) {
        this.listOfItemsUserSpecific = new ArrayList<Item>();
        this.customer = customer;
    }

    public void addItem(Item item) {
        if (item.isAvailable) {
            listOfItemsUserSpecific.add(item);
            System.out.println("add Item " + item.name);
        }
    }
}

class Platform {
    ArrayList<Item> listOfItems;
    String name;

    public Platform(String name) {
        this.listOfItems = new ArrayList<Item>();
        this.name = name;
    }

}

class Order {
    int orderId;
    Customer customer;
    ArrayList<Item> listofItems;
    OrderStatus currentStatus;
    private ArrayList<NotificationInterface> notifiers;

    public Order(int orderId, Cart cart) {
        this.orderId = orderId;
        this.customer = cart.customer;
        this.listofItems = cart.listOfItemsUserSpecific;
        this.currentStatus = OrderStatus.PLACED;
        this.notifiers = new ArrayList<>();
    }

    public void addNotifier(NotificationInterface notifier) {
        notifiers.add(notifier);
    }

    public void removeNotifier(NotificationInterface notifier) {
        notifiers.remove(notifier);
    }

    private void notifySubscribers() {
        // Construct the message once
        String message = "Hello " + customer.name + ", your Order #" + orderId + " is now " + currentStatus;

        // Blast it out to every subscribed service
        for (NotificationInterface notifier : notifiers) {
            notifier.sendNotification(message);
        }
    }

    // The core trigger: changing the status automatically sends notifications
    public void setStatus(OrderStatus newStatus) {
        this.currentStatus = newStatus;
        System.out.println("\n[System] Update: Order " + orderId + " changed to " + newStatus);

        notifySubscribers();
    }

}


public class NotificationSystem {
    public static void main(String[] args) {
        // 1. Setup Platform and Items
        Platform amazon = new Platform("Amazon");
        Item laptop = new Item(101, "MacBook Pro", true);
        Item mouse = new Item(102, "Wireless Mouse", true);

        // 2. Setup Customer and Cart
        Customer customer = new Customer(1, "Amartya");
        Cart cart = new Cart(customer);
        cart.addItem(laptop);
        cart.addItem(mouse);

        // 3. Customer places an Order from their Cart
        Order myOrder = new Order(999, cart);

        // 4. Setup Customer Notification Preferences
        NotificationInterface email = new EmailNotification();
        NotificationInterface sms = new SMSNotification();
        NotificationInterface whatsapp = new WhatsAppNotification();

        // 5. Attach preferences to the order (Amartya wants Email and WhatsApp)
        myOrder.addNotifier(email);
        myOrder.addNotifier(whatsapp);

        // 6. Simulate the order lifecycle!
        // Notice how the notifications happen automatically under the hood.
        System.out.println("\n--- Processing Order Lifecycle ---");

        myOrder.setStatus(OrderStatus.SHIPPED);

        // Amartya decides he also wants SMS updates right before delivery
        myOrder.addNotifier(sms);

        myOrder.setStatus(OrderStatus.OUT_FOR_DELIVERY);

        myOrder.setStatus(OrderStatus.DELIVERED);
    }
}