package assessmentCarProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

    // Insertion sort by year, oldest to newest
    public static void insertionSort(ArrayList<Car> list) {
        for (int i = 1; i < list.size(); i++) {
            Car key = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j).getYear() > key.getYear()) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }

    // Binary search: index of the FIRST car with targetYear, or -1 if none
    public static int binarySearchFirst(ArrayList<Car> list, int targetYear) {
        int low = 0;
        int high = list.size() - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int midYear = list.get(mid).getYear();

            if (midYear == targetYear) {
                result = mid;
                high = mid - 1;
            } else if (midYear < targetYear) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // Menu option 1
    public static void sortAndShow(ArrayList<Car> working) {
        insertionSort(working);
        System.out.println("\nCars sorted by year (oldest to newest). First 10:");
        for (int i = 0; i < 10 && i < working.size(); i++) {
            System.out.println(working.get(i));
        }
    }

    // Menu option 2
    public static void searchByYear(ArrayList<Car> working, int target) {
        int index = binarySearchFirst(working, target);

        if (index == -1) {
            System.out.println("No cars found from " + target + ".");
            return;
        }

        System.out.println("\nCars from " + target + ":");
        int count = 0;
        while (index < working.size() && working.get(index).getYear() == target) {
            System.out.println(working.get(index));
            index++;
            count++;
        }
        System.out.println(count + " car(s) found.");
    }

    public static void main(String[] args) {
        ArrayList<Car> cars = loadCars("Car_Data.csv");
        ArrayList<Car> working = new ArrayList<>(cars.subList(0, 2000));

        Scanner input = new Scanner(System.in);
        boolean sorted = false;
        boolean running = true;

        while (running) {
            System.out.println("\n===== Year Explorer =====");
            System.out.println("1. Sort cars by Year");
            System.out.println("2. Search for cars by Year");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    sortAndShow(working);
                    sorted = true;
                    break;

                case "2":
                    if (!sorted) {                       // binary search needs sorted data
                        insertionSort(working);
                        sorted = true;
                        System.out.println("(Sorted the list by year first.)");
                    }
                    System.out.print("Enter a year (for example, 2020): ");
                    try {
                        int year = Integer.parseInt(input.nextLine().trim());
                        searchByYear(working, year);
                    } catch (NumberFormatException e) {
                        System.out.println("That's not a valid year. Please enter a number.");
                    }
                    break;

                case "3":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Please enter 1, 2, or 3.");
            }
        }
        input.close();
    }
    //menu
}