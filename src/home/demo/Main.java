package home.demo;

import java.sql.*;
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
            statement.execute("""
                    CREATE TABLE IF NOT EXIST 'cars' (
                      'id' INTEGER NOT NULL PRIMARY KEY,
                      'VIN' VARCHAR(17),
                      'brand' VARCHAR(20),
                      'model' VARCHAR(20),
                      'color' VARCHAR(20),
                      'year' INTEGER,
                      current_owner INTEGER);""");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement.execute("""
                    CREATE TABLE IF NOT EXIST 'owners' (
                      'id' INTEGER NOT NULL PRIMARY KEY,
                      'first_name' VARCHAR(20),
                      'middle_name' VARCHAR(20),
                      'last_name' VARCHAR(20),
                      'passport' VARCHAR(6),
                      'address' TEXT,
                      'birthday' DATE);""");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement.execute("""
                    CREATE TABLE IF NOT EXIST 'car_ow' (
                      'car_id' INTEGER NOT NULL,
                      'owner_id' INTEGER NOT NULL,
                      FOREIGN KEY ('car_id') REFERENCES 'cars'('id'),
                      FOREIGN KEY ('owner_id') REFERENCES 'owners'('id'));""");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectQuery() {
        try {
            resultSet = statement.executeQuery(getQuery());

            while (resultSet.next()) {
                String first_name = resultSet.getString("first_name");
                String middle_name = resultSet.getString("middle_name");
                String last_name = resultSet.getString("last_name");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                System.out.printf("%s  %s  %s  %s  %s\n", first_name, middle_name, last_name, brand, model);
            }
        }
        catch (SQLException e) {
            System.out.println("Error SQL");
        }
        finally {
            closeDB();
        }
    }

    public static String getQuery() {
        StringBuilder result = new StringBuilder("SELECT first_name, middle_name, last_name, brand, model\n");
        result.append("FROM owners JOIN car_ow ON owner_id = owners.id\n");
        result.append("JOIN cars ON cars.id = car_id\n");
        result.append("WHERE current_owner = owner_id;");
        
        return result.toString();
    }
	
	public static void closeDB() {
		try { connection.close(); } catch (SQLException e) {}
        try { statement.close(); } catch (SQLException e) {}
        try { resultSet.close(); } catch (SQLException e) {}
	}

	public static void connectDB() {
		try {
            connection = DriverManager.getConnection(DB_URL, user, pass);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public static void generateTables() {
		
		// ===================== Генерация машин ==============
        int count = 5;
        ArrayList<Car> cars = Car.generate(count);
		
		// ===================== Генерация собственников ==============
        count = 8;
        ArrayList<Owner> owners = Owner.generate(count);

        // ================= Заполнение таблицы cars ==============

		for (Car car : cars) { // Текущие влыдельцы
			car.setCurrentOwner((int) Math.floor(Math.random() * owners.size() + 1));
		}

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
            query.append(car.getId());
            query.append(", ");
            query.append(car.getCurrentOwner());
            query.append(')');
            query.append(',');
        }
        // Cтарыe владельцы
        count = (int) Math.floor(Math.random() * cars.size() * owners.size() + cars.size());
        for (int i = 0; i < count; i++) {
            query.append("(");
			query.append(i);  // car_id
            query.append(", ");
			int tmp = 0;
			do {
				tmp = (int) Math.floor(Math.random() * owners.size() + 1);
			} while (cars.get(i + 1).getCurrentOwner() == tmp);
            query.append(tmp); // owner_id
            query.append(")");
            query.append((i < count - 1) ? ',' : ';');
        }
        try {
            statement.execute(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

    public static void main(String[] args) {
		
        connectDB(); 
        createTables(statement); // Создание
		generateTables(); // Заполнение

		selectQuery();

        closeDB(); // Закрытие БД
    }
}

