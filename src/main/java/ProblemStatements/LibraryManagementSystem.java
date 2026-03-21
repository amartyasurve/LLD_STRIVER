package ProblemStatements;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

// --- Enums & Entities ---

enum BookType {
    STANDARD_BOOK, REFERENCE_BOOK, CHILDREN_BOOK
}

class Student {
    private String studentId;
    private String name;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }
    public String getName() { return name; }
}

class Book {
    private String bookId;
    private String title;
    private BookType type;
    private boolean isAvailable;

    public Book(String bookId, String title, BookType type) {
        this.bookId = bookId;
        this.title = title;
        this.type = type;
        this.isAvailable = true;
    }

    public String getTitle() { return title; }
    public BookType getType() { return type; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}

class BookLending {
    private String lendingId;
    private Book book;
    private Student student;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public BookLending(Book book, Student student, LocalDate checkoutDate, int checkoutDays) {
        this.lendingId = UUID.randomUUID().toString().substring(0, 8);
        this.book = book;
        this.student = student;
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(checkoutDays);
    }

    public Book getBook() { return book; }
    public Student getStudent() { return student; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}

// --- Strategies & Factory ---

interface FineCalculationStrategy {
    double calculateFine(BookLending lendingRecord);
}

class ChildrenFineCalculationStrategy implements FineCalculationStrategy {
    public double calculateFine(BookLending lendingRecord) {
        long daysLate = ChronoUnit.DAYS.between(lendingRecord.getDueDate(), lendingRecord.getReturnDate());
        if (daysLate <= 0) return 0.0;
        return daysLate * 2.0; // ₹2 per day for kids
    }
}

class StandardFineCalculationStrategy implements FineCalculationStrategy {
    public double calculateFine(BookLending lendingRecord) {
        long daysLate = ChronoUnit.DAYS.between(lendingRecord.getDueDate(), lendingRecord.getReturnDate());
        if (daysLate <= 0) return 0.0;
        return daysLate * 5.0; // ₹5 per day for standard
    }
}

class ReferenceFineCalculationStrategy implements FineCalculationStrategy {
    public double calculateFine(BookLending lendingRecord) {
        long daysLate = ChronoUnit.DAYS.between(lendingRecord.getDueDate(), lendingRecord.getReturnDate());
        if (daysLate <= 0) return 0.0;
        return daysLate * 50.0; // ₹50 per day! Reference books are highly restricted.
    }
}

class FineStrategyFactory {
    public static FineCalculationStrategy getStrategy(BookType type) {
        switch (type) {
            case CHILDREN_BOOK: return new ChildrenFineCalculationStrategy();
            case STANDARD_BOOK: return new StandardFineCalculationStrategy();
            case REFERENCE_BOOK: return new ReferenceFineCalculationStrategy();
            default: throw new IllegalArgumentException("Unknown Book Type");
        }
    }
}

// --- Core Library System ---

class Library {
    private List<Book> books;
    private List<BookLending> activeLoans;

    public Library() {
        this.books = new ArrayList<>();
        this.activeLoans = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public BookLending issueBook(Student student, Book book) {
        if (!book.isAvailable()) {
            System.out.println("Sorry, '" + book.getTitle() + "' is currently checked out.");
            return null;
        }

        // Standard issue time is 14 days
        BookLending lending = new BookLending(book, student, LocalDate.now(), 14);
        book.setAvailable(false);
        activeLoans.add(lending);

        System.out.println("Issued '" + book.getTitle() + "' to " + student.getName() + ". Due on: " + lending.getDueDate());
        return lending;
    }

    // We pass in the return date here just so we can simulate time travel in the main method!
    public void returnBook(BookLending lendingRecord, LocalDate actualReturnDate) {
        lendingRecord.setReturnDate(actualReturnDate);
        Book returnedBook = lendingRecord.getBook();

        System.out.println("\n--- Processing Return ---");
        System.out.println("Student: " + lendingRecord.getStudent().getName());
        System.out.println("Book: " + returnedBook.getTitle());

        // 1. Ask the Factory for the right math rules
        FineCalculationStrategy strategy = FineStrategyFactory.getStrategy(returnedBook.getType());

        // 2. Calculate the exact fine
        double fine = strategy.calculateFine(lendingRecord);

        if (fine > 0) {
            System.out.println("Status: LATE. Total Fine: ₹" + fine);
        } else {
            System.out.println("Status: ON TIME. No fine.");
        }

        // 3. Clean up the system state
        returnedBook.setAvailable(true);
        activeLoans.remove(lendingRecord);
        System.out.println("Book is back on the shelf.");
    }
}

// --- Main Application ---

public class LibraryManagementSystem {
    public static void main(String[] args) {
        // 1. Setup the Library
        Library library = new Library();

        Book harryPotter = new Book("B1", "Harry Potter", BookType.CHILDREN_BOOK);
        Book cleanCode = new Book("B2", "Clean Code", BookType.STANDARD_BOOK);
        Book encyclopedia = new Book("B3", "World Encyclopedia", BookType.REFERENCE_BOOK);

        library.addBook(harryPotter);
        library.addBook(cleanCode);
        library.addBook(encyclopedia);

        Student student = new Student("S1", "Amartya");

        System.out.println("=== Day 1: Checking out books ===");
        BookLending loan1 = library.issueBook(student, harryPotter);
        BookLending loan2 = library.issueBook(student, cleanCode);

        // 2. Time Travel Simulation!
        // The books are due in 14 days. Let's pretend Amartya brings them back 20 days from today (6 days late).
        LocalDate futureReturnDate = LocalDate.now().plusDays(20);

        System.out.println("\n... 20 days later ...");

        // 3. Process Returns
        // Harry Potter is a Children's Book (6 days late * ₹2 = ₹12)
        library.returnBook(loan1, futureReturnDate);

        // Clean Code is a Standard Book (6 days late * ₹5 = ₹30)
        library.returnBook(loan2, futureReturnDate);
    }
}
