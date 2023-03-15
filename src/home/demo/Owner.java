package home.demo;

public class Owner {
    long id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String passport;
    private String address;
    private String birthday;

    public Owner(long id, String first_name, String middle_name, String last_name, String passport, String address, String birthday) {
        this.id = id;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.passport = passport;
        this.address = address;
        this.birthday = birthday;
    }
	
	public ArrayList<Owner> generate() {
		
// 		Исходные данные
		
		String[] first_names = {"Ivan", "Peter", "Sidor", "Alexander"};
        String[] middle_names = {"Ivanovich", "Petrovich", "Sidorovich", "Alexandrovich"};
        String[] last_names = {"Ivanov", "Petrov", "Sidorov", "Sokolov"};
        String[] cities = {"Moskva", "Ivanovo", "Vladimir region, Kovrov"};
        String[] streets = {"Mohovaya street", "Fridrikha Engelsa street", "The 2nd Line", "Severnyi Trakt", "Lenina street"};

//		Генерация
		
		ArrayList<Owner> result = new ArrayList<>();
		
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
		return result;
	}

    @Override
    public String toString() {
        return "(" + id +
                ", '" + first_name + '\'' +
                ", '" + middle_name + '\'' +
                ", '" + last_name + '\'' +
                ", '" + passport + '\'' +
                ", '" + address + '\'' +
                ", '" + birthday + '\'' +
                ')';
    }
}
