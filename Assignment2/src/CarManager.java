import java.io.*;
import java.util.*;

class Car {
    int id;
    String make;
    String model;
    int yearOfManufacture;
    String color;
    double price;
    String registrationNumber;

    public Car(int id, String make, String model, int yearOfManufacture, String color, double price, String registrationNumber) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.color = color;
        this.price = price;
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return String.format("Car{id=%d, make='%s', model='%s', yearOfManufacture=%d, color='%s', price=%.2f, registrationNumber='%s'}",
                id, make, model, yearOfManufacture, color, price, registrationNumber);
    }
}

public class CarManager {
    // Save list of cars to a file
    public static void saveToFile(List<Car> carList, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Car car : carList) {
                writer.write(car.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Filter by make (brand)
    public static List<Car> filterByMake(Car[] cars, String make) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.make.equalsIgnoreCase(make)) {
                result.add(car);
            }
        }
        return result;
    }

    // Filter by model and usage more than n years
    public static List<Car> filterByModelAndUsage(Car[] cars, String model, int yearsInUse, int currentYear) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.model.equalsIgnoreCase(model) && (currentYear - car.yearOfManufacture > yearsInUse)) {
                result.add(car);
            }
        }
        return result;
    }

    // Filter by year of manufacture and price greater than the specified one
    public static List<Car> filterByYearAndPrice(Car[] cars, int year, double price) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.yearOfManufacture == year && car.price > price) {
                result.add(car);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Car[] cars = {
                new Car(1, "Toyota", "Camry", 2015, "Black", 15000, "ABC123"),
                new Car(2, "Honda", "Civic", 2010, "White", 8000, "XYZ456"),
                new Car(3, "Toyota", "Corolla", 2017, "Blue", 12000, "DEF789"),
                new Car(4, "BMW", "X5", 2018, "Black", 50000, "GHI012"),
                new Car(5, "Honda", "Accord", 2015, "Red", 16000, "JKL345")
        };

        int currentYear = 2024;
        

        // Example: List of cars of a given brand
        List<Car> toyotaCars = filterByMake(cars, "Toyota");
        saveToFile(toyotaCars, "toyota_cars.txt");

        // Example: List of cars of a given model that have been in use for more than n years
        List<Car> hondaCivicOverYears = filterByModelAndUsage(cars, "Civic", 10, currentYear);
        saveToFile(hondaCivicOverYears, "civic_over_10_years.txt");

        // Example: List of cars of a given year with price higher than specified
        List<Car> cars2015AbovePrice = filterByYearAndPrice(cars, 2015, 14000);
        saveToFile(cars2015AbovePrice, "cars_2015_above_14000.txt");
        
        System.out.println("Current Directory: " + System.getProperty("user.dir"));

    }
    
}
