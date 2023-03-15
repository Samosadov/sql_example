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
	
	public ArrayList<Car> generate(int count) {
		
//      Исходные данные

        String[] brands = {"Volvo", "Toyota", "LADA"};
        String[] models = {"Granta", "Kalina", "Priora"};
        int year = 2005;
        String[] colors = {"white", "black", "red", "green"};
		
//		Генерация

        ArrayList<Car> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            StringBuilder VIN = new StringBuilder();
            for (int k = 0; k < 17; k++) VIN.append((char) Math.floor(Math.random() * 10 + 48));

            cars.add(new Car(i + 1, VIN.toString(),
                    brands[(int) Math.floor(Math.random() * brands.length)],
                    models[(int) Math.floor(Math.random() * models.length)],
                    year + (int) Math.floor(Math.random() * 10),
                    colors[(int) Math.floor(Math.random() * colors.length)]));
        }		
		
		return result;
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
