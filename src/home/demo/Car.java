package home.demo;

public class Car {
    private long id;
    private String VIN;
    private String brand;
    private String model;
    private int year;
    private String color;

    public Car(long id, String VIN, String brand, String model, int year, String color) {
        this.id = id;
        this.VIN = VIN;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", VIN='" + VIN + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                '}';
    }
}
