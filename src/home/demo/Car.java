package home.demo;

public class Car {
    private long id;
    private String VIN;
    private String brand;
    private String model;
    private int year;
    private String color;
    private long currentOwner;

    public Car(long id, String VIN, String brand, String model, int year, String color) {
        this.id = id;
        this.VIN = VIN;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public long getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(long currentOwner) {
        this.currentOwner = currentOwner;
    }

    @Override
    public String toString() {
        return "(" + id +
                ", '" + VIN + '\'' +
                ", '" + brand + '\'' +
                ", '" + model + '\'' +
                ", " + year +
                ", '" + color + '\'' +
                ", " + currentOwner + ')';
    }
}
