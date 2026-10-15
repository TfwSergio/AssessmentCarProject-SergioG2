package assessmentCarProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static ArrayList<Car> loadCars(String filename) {
        ArrayList<Car> cars = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();          // header row: read it and ignore it

            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(",");

                    if (parts.length != 7) {
                        continue;
                    }

                    String carId = parts[0].trim();
                    String brand = parts[1].trim();
                    String model = parts[2].trim();
                    int year = Integer.parseInt(parts[3].trim());
                    String fuelType = parts[4].trim();
                    String color = parts[5].trim();
                    double mileage = Double.parseDouble(parts[6].trim());

                    cars.add(new Car(carId, brand, model, year, fuelType, color, mileage));

                } catch (NumberFormatException e) {
                    // bad number in this row: skip it, don't crash
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }

        System.out.println("Total cars loaded: " + cars.size());
        return cars;
    }

    public static void main(String[] args) {
        ArrayList<Car> cars = loadCars("Car_Data.csv");
        ArrayList<Car> working = new ArrayList<>(cars.subList(0, 2000));
        System.out.println(working.get(0));
    }
}
