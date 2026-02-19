package PracticeSession02.DecoratorPattern;
/*
* Awesome. Let's switch gears to the **Decorator pattern**.

As a software engineer, you've probably run into situations where you need to add responsibilities to individual objects dynamically, without affecting other objects. If we try to do this purely through inheritance, we often end up with an exploding class hierarchy (e.g., `EmailNotifier`, `EmailAndSMSNotifier`, `EmailSMSAndSlackNotifier`, etc.).

The Decorator pattern is the perfect solution for this, favoring composition over inheritance.

### The Scenario

Let's stick with our logistics company. Whenever an urgent shipment is delayed, we need to alert the on-call logistics managers.

Originally, our system only supported sending **Email** notifications. Now, depending on the severity of the alert and the specific manager's preferences, we might need to send an **SMS**, a **Slack message**, or a combination of all three!

Here is the base code we are starting with:

**1. The Component Interface:**

```java
public interface Notifier {
    void send(String message);
}

```

**2. The Concrete Component:**

```java
public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

```

### Your Task

I want you to use the Decorator pattern so we can dynamically "stack" these notification methods.

You'll need to create:

1. A base **Decorator** class that implements `Notifier`.
2. Two concrete decorators: **`SMSNotifier`** and **`SlackNotifier`**.
3. A `main` method simulating a client. In the client, I want you to configure a notification stack that sends an **Email**, then an **SMS**, and finally a **Slack** message, all from a single `send()` call.

**Could you write out the Java code for the decorators and the `main` method to demonstrate how they chain together?** Take your time, and let me know if you have any questions before you start!
* */

interface Notifier {
    public void send(String message);
}

//concerte classes
class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("SENT VIA EMAIL");
        System.out.println(message);
    }
}

class SMSNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("SENT AN SMS : " + message);
    }
}

class SlackNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("SENT AN SLACK MESSAGE : " + message);
    }
}

abstract class NotifierDecorator implements Notifier {
    Notifier notifier;

    NotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }
    @Override
    public void send(String message){
        notifier.send(message);
    }
}

class AddSMS extends NotifierDecorator {
    AddSMS(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        System.out.println("Sent Via SMS");
        super.send(message);
    }
}

class AddSlack extends NotifierDecorator {
    AddSlack(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        System.out.println("Sent Via Slack");
        super.send(message);
    }
}


//single send should send mail sms and slack

public class DecoratorDesignPattern {
    public static void main(String[] args) {
        Notifier notifier = new AddSlack(new AddSMS(new EmailNotifier()));
        notifier.send("send HI");
    }
}



