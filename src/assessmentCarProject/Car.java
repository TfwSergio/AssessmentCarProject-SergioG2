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

    
    
    public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public int getYear() {
		return year;
	}



	public void setYear(int year) {
		this.year = year;
	}



	public String getFuelType() {
		return fuelType;
	}



	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}



	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public double getMileage() {
		return mileage;
	}



	public void setMileage(double mileage) {
		this.mileage = mileage;
	}



	public void setCarId(String carId) {
		this.carId = carId;
	}



	// getters for every field, e.g.:
    public String getCarId() { return carId; }
    // ... (Eclipse can generate these: Source → Generate Getters and Setters)

    @Override
    public String toString() {
        return carId + " | " + brand + " " + model + " | " + year + " | "
             + fuelType + " | " + color + " | " + mileage + " kmpl";
    }
    
    //these are the setters and getters needed to identify the car and to format the information properly
    
}