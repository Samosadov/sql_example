package home.demo;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String[] first_names = {"Ivan", "Peter", "Sidor", "Alexander"};
        String[] middle_names = {"Ivanovich", "Petrovich", "Sidorovich", "Alexandrovich"};
        String[] last_names = {"Ivanov", "Petrov", "Sidorov", "Sokolov"};
        String[] cities = {"Moskva", "Ivanovo", "Vladimir region, Kovrov"};
        String[] streets = {"Mohovaya street", "Fridriha Engelsa street", "The 2nd Line", "Severnyi Trakt", "Lenina street"};

        String[] brands = {"Volvo", "Toyota", "LADA"};
        String[] models = {"Granta", "Kalina", "Priora"};
        int year = 2005;
        String[] colors = {"white", "black", "red", "green"};

        int count = 5;
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            StringBuilder VIN = new StringBuilder();
            for (int k = 0; k < 17; k++) VIN.append((char) Math.floor(Math.random() * 10 + 48));

            cars.add(new Car(i + 1, VIN.toString(),
                    brands[(int) Math.floor(Math.random() * brands.length)],
                    models[(int) Math.floor(Math.random() * models.length)],
                    year + (int) Math.floor(Math.random() * 10),
                    colors[(int) Math.floor(Math.random() * colors.length)]));
        }
        for (Car car : cars) System.out.println(car);

        count = 8;
        ArrayList<Owner> owners = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            StringBuilder name = new StringBuilder();
            name.append(first_names[(int) Math.floor(Math.random() * first_names.length)]);
            name.append(" ");
            name.append(middle_names[(int) Math.floor(Math.random() * middle_names.length)]);
            name.append(" ");
            name.append(last_names[(int) Math.floor(Math.random() * last_names.length)]);

            StringBuilder passport = new StringBuilder();
            passport.append((int) Math.floor(Math.random() * 9000 + 1000));
            passport.append(" ");
            passport.append((int) Math.floor(Math.random() * 900000 + 100000));

            StringBuilder address = new StringBuilder();
            address.append(cities[(int) Math.floor(Math.random() * cities.length)]);
            address.append(", ");
            address.append(streets[(int) Math.floor(Math.random() * streets.length)]);
            address.append(", ");
            address.append((int) Math.floor(Math.random() * 200 + 1));
            address.append(", ");
            address.append((int) Math.floor(Math.random() * 200 + 1));

            StringBuilder birthday = new StringBuilder();
            birthday.append((int) Math.floor(Math.random() * 55 + 1950));
            birthday.append("-");
            birthday.append(new DecimalFormat("00").format(Math.floor(Math.random() * 12 + 1)));
            birthday.append("-");
            birthday.append(new DecimalFormat("00").format(Math.floor(Math.random() * 28 + 1)));

            System.out.println(birthday);

            owners.add(new Owner(i + 1, name.toString(), passport.toString(), address.toString(), birthday.toString()));
        }
        for (Owner owner : owners) System.out.println(owner);
    }
}

