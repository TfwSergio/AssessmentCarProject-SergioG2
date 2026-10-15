//Sergio Garcia 
package assessmentCarProject;

public class Car {
    private String carId;
    private String brand;
    private String model;
    private int year;
    private String fuelType;
    private String color;
    private double mileage;

    public Car(String carId, String brand, String model, int year,
               String fuelType, String color, double mileage) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.color = color;
        this.mileage = mileage;
    }

    // getters for every field, e.g.:
    public String getCarId() { return carId; }
    // ... (Eclipse can generate these: Source → Generate Getters and Setters)

    @Override
    public String toString() {
        return carId + " | " + brand + " " + model + " | " + year + " | "
             + fuelType + " | " + color + " | " + mileage + " kmpl";
    }
}