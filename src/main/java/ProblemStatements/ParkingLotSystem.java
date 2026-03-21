package ProblemStatements;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.*;

// --- Enums & Data Entities ---

enum VehicleType {
    TWO_WHEELER, THREE_WHEELER, FOUR_WHEELER
}

class Vehicle {
    private String numberPlate;
    private VehicleType type;

    public Vehicle(String numberPlate, VehicleType type) {
        this.numberPlate = numberPlate;
        this.type = type;
    }
    public String getNumberPlate() { return numberPlate; }
    public VehicleType getType() { return type; }
}

class ParkingSpot {
    private String spotId;
    private boolean isAvailable;
    private VehicleType spotSize;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotId, VehicleType spotSize) {
        this.spotId = spotId;
        this.spotSize = spotSize;
        this.isAvailable = true;
    }

    public String getSpotId() { return spotId; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public VehicleType getSpotSize() { return spotSize; }
    public void setParkedVehicle(Vehicle vehicle) { this.parkedVehicle = vehicle; }
}

class Ticket {
    private String ticketId;
    private ParkingSpot parkedSpot;
    private Vehicle vehicle;
    private LocalDateTime entryTime;

    // Helper method to simulate time passing for our example
    public int getHoursParked() {
        // In a real app: return (int) Duration.between(entryTime, LocalDateTime.now()).toHours();
        return 2; // Hardcoded to 2 hours for demonstration
    }

    public void setTicketId(String ticketId) { this.ticketId = ticketId; }
    public ParkingSpot getParkedSpot() { return parkedSpot; }
    public void setParkedSpot(ParkingSpot spot) { this.parkedSpot = spot; }
    public Vehicle getVehicle() { return vehicle; }
    public String getTicketId(){return ticketId;}
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }
    public void setEntryTime(LocalDateTime time) { this.entryTime = time; }
}

// --- Structural Entities ---

class ParkingFloor {
    private String floorName;
    private List<ParkingSpot> spots;

    public ParkingFloor(String floorName) {
        this.floorName = floorName;
        this.spots = new ArrayList<>();
    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    public ParkingSpot findAvailableSpot(VehicleType type) {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable() && spot.getSpotSize() == type) {
                return spot;
            }
        }
        return null;
    }
}

class ParkingLot {
    private List<ParkingFloor> floors;

    public ParkingLot() {
        this.floors = new ArrayList<>();
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public ParkingSpot getAvailableSpot(VehicleType type) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.findAvailableSpot(type);
            if (spot != null) return spot;
        }
        return null; // Lot is completely full for this type
    }
}

// --- Strategies & Factory ---

interface PricingStrategy {
    double calculatePrice(Ticket ticket);
}

class TwoWheelerPricingStrategy implements PricingStrategy {
    public double calculatePrice(Ticket ticket) {
        return ticket.getHoursParked() * 10.0;
    }
}

class FourWheelerPricingStrategy implements PricingStrategy {
    public double calculatePrice(Ticket ticket) {
        return ticket.getHoursParked() * 20.0;
    }
}

class PricingStrategyFactory {
    public static PricingStrategy getPricingStrategy(VehicleType type) {
        switch (type) {
            case TWO_WHEELER: return new TwoWheelerPricingStrategy();
            case FOUR_WHEELER: return new FourWheelerPricingStrategy();
            default: throw new IllegalArgumentException("Unknown vehicle type");
        }
    }
}

// --- Gates ---

class EntryGate {
    private ParkingLot parkingLot;

    public EntryGate(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket generateTicket(Vehicle vehicle) {
        ParkingSpot spot = parkingLot.getAvailableSpot(vehicle.getType());

        if (spot == null) {
            System.out.println("Sorry, no spots available for " + vehicle.getType());
            return null;
        }

        spot.setAvailable(false);
        spot.setParkedVehicle(vehicle);

        Ticket ticket = new Ticket();
        ticket.setTicketId(UUID.randomUUID().toString().substring(0, 8));
        ticket.setParkedSpot(spot);
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(LocalDateTime.now());

        System.out.println("Ticket Generated: " + ticket.getTicketId() + " at Spot: " + spot.getSpotId());
        return ticket;
    }
}

class ExitGate {
    public void processExit(Ticket ticket) {
        PricingStrategy strategy = PricingStrategyFactory.getPricingStrategy(ticket.getVehicle().getType());
        double amount = strategy.calculatePrice(ticket);

        System.out.println("Vehicle " + ticket.getVehicle().getNumberPlate() + " exiting.");
        System.out.println("Total Fee for " + ticket.getHoursParked() + " hours: ₹" + amount);

        ParkingSpot spot = ticket.getParkedSpot();
        spot.setAvailable(true);
        spot.setParkedVehicle(null);
        System.out.println("Spot " + spot.getSpotId() + " is now free.");
    }
}

// --- Main Application ---

public class ParkingLotSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Setup System
        ParkingLot lot = new ParkingLot();
        EntryGate entryGate = new EntryGate(lot);
        ExitGate exitGate = new ExitGate();

        // Admin Setup (Adding dummy data)
        ParkingFloor floor1 = new ParkingFloor("Floor 1");
        floor1.addSpot(new ParkingSpot("A1", VehicleType.TWO_WHEELER));
        floor1.addSpot(new ParkingSpot("B1", VehicleType.FOUR_WHEELER));
        lot.addFloor(floor1);

        // We will store the active ticket here to simulate the user holding it
        Ticket activeTicket = null;

        boolean running = true;
        while (running) {
            System.out.println("\n--- Parking System ---");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Exit Parking");
            System.out.println("3. Quit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter Plate Number: ");
                String plate = scanner.next();

                // For simplicity, we just assume 4-wheeler here
                Vehicle car = new Vehicle(plate, VehicleType.FOUR_WHEELER);
                activeTicket = entryGate.generateTicket(car);

            } else if (choice == 2) {
                if (activeTicket != null) {
                    exitGate.processExit(activeTicket);
                    activeTicket = null; // Ticket used
                } else {
                    System.out.println("No active ticket found. Please park first!");
                }
            } else {
                running = false;
            }
        }
        scanner.close();
    }
}
