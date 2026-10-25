/**
 * Vehicle Management System
 *
 * This program is a console-based application that allows a car rental agency
 * to manage different types of vehicles: Cars, Motorcycles, and Trucks.
 * It demonstrates object-oriented programming principles such as interfaces,
 * inheritance, encapsulation, and polymorphism.
 *
 * The user can select a vehicle type, input its details, and view a summary.
 */

import java.util.Scanner;

interface Vehicle {
    String getMake();
    String getModel();
    int getYear();
}

interface CarVehicle {
    void setNumberOfDoors(int doors);
    int getNumberOfDoors();
    void setFuelType(String fuelType);
    String getFuelType();
}

interface MotorVehicle {
    void setNumberOfWheels(int wheels);
    int getNumberOfWheels();
    void setMotorcycleType(String type);
    String getMotorcycleType();
}

interface TruckVehicle {
    void setCargoCapacity(double capacity);
    double getCargoCapacity();
    void setTransmissionType(String transmission);
    String getTransmissionType();
}

class Car implements Vehicle, CarVehicle {
    private String make, model, fuelType;
    private int year, numberOfDoors;

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public void setNumberOfDoors(int doors) { this.numberOfDoors = doors; }
    public int getNumberOfDoors() { return numberOfDoors; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    public String getFuelType() { return fuelType; }
}

class Motorcycle implements Vehicle, MotorVehicle {
    private String make, model, motorcycleType;
    private int year, numberOfWheels;

    public Motorcycle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public void setNumberOfWheels(int wheels) { this.numberOfWheels = wheels; }
    public int getNumberOfWheels() { return numberOfWheels; }
    public void setMotorcycleType(String type) { this.motorcycleType = type; }
    public String getMotorcycleType() { return motorcycleType; }
}

class Truck implements Vehicle, TruckVehicle {
    private String make, model, transmissionType;
    private int year;
    private double cargoCapacity;

    public Truck(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public void setCargoCapacity(double capacity) { this.cargoCapacity = capacity; }
    public double getCargoCapacity() { return cargoCapacity; }
    public void setTransmissionType(String transmission) { this.transmissionType = transmission; }
    public String getTransmissionType() { return transmissionType; }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose vehicle type:");
            System.out.println("1. Car");
            System.out.println("2. Motorcycle");
            System.out.println("3. Truck");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Car Make: ");
                    String carMake = scanner.nextLine();
                    System.out.print("Enter Car Model: ");
                    String carModel = scanner.nextLine();
                    System.out.print("Enter Car Year: ");
                    int carYear = scanner.nextInt();
                    scanner.nextLine();

                    Car car = new Car(carMake, carModel, carYear);
                    System.out.print("Enter Number of Doors: ");
                    car.setNumberOfDoors(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Enter Fuel Type (petrol/diesel/electric): ");
                    car.setFuelType(scanner.nextLine());

                    System.out.println("\n--- Vehicle Details ---");
                    System.out.println("Make: " + car.getMake());
                    System.out.println("Model: " + car.getModel());
                    System.out.println("Year: " + car.getYear());
                    System.out.println("Doors: " + car.getNumberOfDoors());
                    System.out.println("Fuel Type: " + car.getFuelType());
                    break;

                case 2:
                    System.out.print("Enter Motorcycle Make: ");
                    String motoMake = scanner.nextLine();
                    System.out.print("Enter Motorcycle Model: ");
                    String motoModel = scanner.nextLine();
                    System.out.print("Enter Motorcycle Year: ");
                    int motoYear = scanner.nextInt();
                    scanner.nextLine();

                    Motorcycle moto = new Motorcycle(motoMake, motoModel, motoYear);
                    System.out.print("Enter Number of Wheels: ");
                    moto.setNumberOfWheels(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Enter Motorcycle Type (sport/cruiser/off-road): ");
                    moto.setMotorcycleType(scanner.nextLine());

                    System.out.println("\n--- Vehicle Details ---");
                    System.out.println("Make: " + moto.getMake());
                    System.out.println("Model: " + moto.getModel());
                    System.out.println("Year: " + moto.getYear());
                    System.out.println("Wheels: " + moto.getNumberOfWheels());
                    System.out.println("Type: " + moto.getMotorcycleType());
                    break;

                case 3:
                    System.out.print("Enter Truck Make: ");
                    String truckMake = scanner.nextLine();
                    System.out.print("Enter Truck Model: ");
                    String truckModel = scanner.nextLine();
                    System.out.print("Enter Truck Year: ");
                    int truckYear = scanner.nextInt();
                    scanner.nextLine();

                    Truck truck = new Truck(truckMake, truckModel, truckYear);
                    System.out.print("Enter Cargo Capacity (tons): ");
                    truck.setCargoCapacity(scanner.nextDouble());
                    scanner.nextLine();
                    System.out.print("Enter Transmission Type (manual/automatic): ");
                    truck.setTransmissionType(scanner.nextLine());

                    System.out.println("\n--- Vehicle Details ---");
                    System.out.println("Make: " + truck.getMake());
                    System.out.println("Model: " + truck.getModel());
                    System.out.println("Year: " + truck.getYear());
                    System.out.println("Cargo Capacity: " + truck.getCargoCapacity() + " tons");
                    System.out.println("Transmission: " + truck.getTransmissionType());
                    break;

                case 4:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
