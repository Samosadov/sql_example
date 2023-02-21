package home.demo;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        long owner_id = 0;
        String[] first_names = {"Ivan", "Peter", "Sidor", "Alexander"};
        String[] middle_names = {"Ivanovich", "Petrovich", "Sidorovich", "Alexandrovich"};
        String[] last_names = {"Ivanov", "Petrov", "Sidorov", "Sokolov"};
        String pass_series = "";
        String pass_number = "";
        String[] cities = {"Moskva", "Ivanovo", "Vladimir region, Kovrov"};
        String[] streets = {"Mohovaya street", "Fridriha Engelsa street", "The 2nd Line", "Severnyi Trakt", "Lenina street"};
        int house = 0;
        int apt = 0;
        String birthday = "";

        long car_id = 0;
        StringBuilder VIN;
        String[] brands = {"Volvo", "Toyota", "LADA"};
        String[] models = {"Granta", "Kalina", "Priora"};
        int year = 2005;
        String[] colors = {"white", "black", "red", "green"};

        int count = 5;
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            VIN = new StringBuilder();
            for (int k = 0; k < 17; k++) VIN.append((char) Math.floor(Math.random() * 10) + 48);

            cars.add(new Car(i + 1, VIN.toString(),
                    brands[(int) Math.floor(Math.random() * brands.length)],
                    models[(int) Math.floor(Math.random() * models.length)],
                    year + (int) Math.floor(Math.random() * 10),
                    colors[(int) Math.floor(Math.random() * colors.length)]));
        }
        for (Car car : cars) System.out.println(car);

        count = 5;
        ArrayList<Owner> owners = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            StringBuilder name = new StringBuilder();
            name.append(first_names[(int) Math.floor(Math.random() * first_names.length)]);
            name.append(" ");
            name.append(middle_names[(int) Math.floor(Math.random() * middle_names.length)]);
            name.append(" ");
            name.append(last_names[(int) Math.floor(Math.random() * last_names.length)]);
            owners.add(new Owner(i + 1, name, passport, address, birthday));
        }
    }
}

