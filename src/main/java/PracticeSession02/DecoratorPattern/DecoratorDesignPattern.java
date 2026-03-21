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
/*Great questions. Understanding the exact execution flow—and how inheritance plays into it—is exactly what separates junior developers from solid mid-level engineers. Let's break both of these down.

1. "The Chain" (The Russian Nesting Dolls)
Think of the Decorator pattern like a set of Russian nesting dolls. When you wrote this line in your main method:

Java
Notifier notifier = new AddSlack(new AddSMS(new EmailNotifier()));
You built a wrapped structure from the inside out:

The Core: EmailNotifier is at the center.

The Middle Layer: AddSMS wraps the EmailNotifier.

The Outer Layer: AddSlack wraps the AddSMS object.

When your client calls notifier.send("HI"), it's actually calling send() on the outermost layer (AddSlack). Here is the exact step-by-step execution chain:

AddSlack.send() starts: It immediately calls super.send(). This pauses AddSlack and passes the baton to the middle layer (AddSMS).

AddSMS.send() starts: It also immediately calls super.send(). This pauses AddSMS and passes the baton to the core (EmailNotifier).

EmailNotifier.send() executes: It doesn't have a super.send() call. It just prints: "SENT AN EMAIL : HI". Its job is done.

Control returns to AddSMS: It resumes right after its super.send() call and prints its own message: "SENT AN SMS : HI". Its job is done.

Control returns to AddSlack: It resumes right after its super.send() call and prints: "SENT A SLACK MESSAGE : HI".

Because the delegation (super.send()) happens before the print statements, the notifications print from the inside out.

2. The Use of the super Keyword
In Java, super refers to the immediate parent class. For your concrete decorators (AddSMS and AddSlack), their parent class is the abstract NotifierDecorator.

Let's look at what the parent class is doing:

Java
abstract class NotifierDecorator implements Notifier {
    Notifier notifier; // This holds the wrapped object

    NotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }

    // The parent's send method
    @Override
    public void send(String message) {
        this.notifier.send(message); // This is the crucial link!
    }
}
When your AddSMS class calls super.send(message), it is literally saying: "Hey Parent Class, run your send() method." The parent class then takes the notifier object it's holding (in this case, the EmailNotifier) and calls send() on it.

Why do we do it this way? Technically, since you made the notifier variable package-private, your AddSMS class could just call this.notifier.send(message) directly. However, standard practice is to make notifier private or protected and let the base NotifierDecorator handle the delegation via super.send(). This enforces the single responsibility principle: the base decorator handles traversing the chain, and the concrete decorators only care about adding their specific new behavior (like sending an SMS).

Does that clear up how the objects communicate with each other? Would you like to quickly rewrite the AddSlack class in the chat using this updated logic, or shall we move on to a quick multithreading question?
* */



