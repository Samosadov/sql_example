package home.demo;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {

    private static final String DB_URL = "jdbc:sqlite:identifier.sqlite";
    private static final String user = "root";
    private static final String pass = "root";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void createTables(Statement statement) {
        try {
            statement.execute("CREATE TABLE 'cars' (" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "\n" +
                    "  'id' INTEGER NOT NULL PRIMARY KEY,\n" +
                    "  'VIN' VARCHAR(17),\n" +
                    "  'brand' VARCHAR(20),\n" +
                    "  'model' VARCHAR(20),\n" +
                    "  'color' VARCHAR(20),\n" +
                    "  'year' INTEGER,\n" +
                    "  current_owner INTEGER);");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement.execute("CREATE TABLE 'owners' (\n" +
                    "  'id' INTEGER NOT NULL PRIMARY KEY,\n" +
                    "  'first_name' VARCHAR(20),\n" +
                    "  'middle_name' VARCHAR(20),\n" +
                    "  'last_name' VARCHAR(20),\n" +
                    "  'passport' VARCHAR(6),\n" +
                    "  'address' TEXT,\n" +
                    "  'birthday' DATE);");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement.execute("CREATE TABLE 'car_ow' (\n" +
                    "  'car_id' INTEGER NOT NULL,\n" +
                    "  'owner_id' INTEGER NOT NULL,\n" +
                    "  FOREIGN KEY ('car_id') REFERENCES 'cars'('id'),\n" +
                    "  FOREIGN KEY ('owner_id') REFERENCES 'owners'('id'));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectQuery() {
        try {
            connection = DriverManager.getConnection(DB_URL, user, pass);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getQuery());

            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String VIN = resultSet.getString("VIN");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("year");
                String color = resultSet.getString("color");
                System.out.printf("%d  %s  %s  %s  %d  %s\n", id, VIN, brand, model, year, color);
            }
        }
        catch (SQLException e) {
            System.out.println("Error SQL");
        }
        finally {
            try { connection.close(); } catch (SQLException e) {}
            try { statement.close(); } catch (SQLException e) {}
            try { resultSet.close(); } catch (SQLException e) {}
        }
    }

    public static String getQuery() {
//        String query = "INSERT INTO 'cars' ('id', 'VIN', 'brand', 'model', 'year', 'color', 'current_owner') VALUES ";
//        for (Car cars : cars) {query += ""
        return "";
    }

    public static void tempGenerator() {

    }

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(DB_URL, user, pass);
            statement = connection.createStatement();
            createTables(statement); // =========== Создание таблиц =============
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // ===================== Генерация машин ==============

        String[] first_names = {"Ivan", "Peter", "Sidor", "Alexander"};
        String[] middle_names = {"Ivanovich", "Petrovich", "Sidorovich", "Alexandrovich"};
        String[] last_names = {"Ivanov", "Petrov", "Sidorov", "Sokolov"};
        String[] cities = {"Moskva", "Ivanovo", "Vladimir region, Kovrov"};
        String[] streets = {"Mohovaya street", "Fridrikha Engelsa street", "The 2nd Line", "Severnyi Trakt", "Lenina street"};

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

//      ===================== Генерация собственников ==============
        count = 8;
        ArrayList<Owner> owners = new ArrayList<>();
        for (int i = 0; i < count; i++) {

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

            owners.add(new Owner(i + 1,
                    first_names[(int) Math.floor(Math.random() * first_names.length)],
                    middle_names[(int) Math.floor(Math.random() * middle_names.length)],
                    last_names[(int) Math.floor(Math.random() * last_names.length)],
                    passport.toString(),
                    address.toString(),
                    birthday.toString()));
        }

        // ================= Заполнение таблицы cars ==============

        StringBuilder query = new StringBuilder("INSERT INTO 'cars' ('id', 'VIN', 'brand', 'model', 'year', 'color', 'current_owner') VALUES ");
        for (Car car : cars) {
            car.setCurrentOwner((int) Math.floor(Math.random() * owners.size() + 1));
            query.append(car);
            query.append(',');
        }
        query.deleteCharAt(query.length());
        query.append(";");
        try {
            statement.execute(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // ================= Заполнение таблицы owners ==============

        query = new StringBuilder("INSERT INTO 'owners' ('id', 'first_name', 'middle_name', 'last_name', 'passport', 'address', 'birthday') VALUES ");
        for (Owner owner : owners) {
            query.append(owner.toString());
            query.append(',');
        }
        query.deleteCharAt(query.length());
        query.append(";");
        try {
            statement.execute(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        ===================== Организация связей ==============

        query = new StringBuilder("INSERT INTO 'car_ow' ('car_id', 'owner_id') VALUES ");
        for (Car car : cars) {
            query.append('(');
            query.append(car.getId() + ", " + car.getCurrentOwner());
            query.append(')');
            query.append(',');
        }
        // Добавить генерацию старых владельцев
        count = (int) Math.floor(Math.random() * cars.size() * owners.size() + cars.size());
        for (int i = 0; i < count; i++) {
            query.append("(");
            query.append((int) Math.floor(Math.random() * cars.size() + 1));
            query.append(", ");
            query.append((int) Math.floor(Math.random() * owners.size() + 1));
            query.append(")");
            query.append((i < count - 1) ? ',' : ';');
        }
        try {
            statement.execute(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try { connection.close(); } catch (SQLException e) {}
        try { statement.close(); } catch (SQLException e) {}
        try { resultSet.close(); } catch (SQLException e) {}
    }
}

