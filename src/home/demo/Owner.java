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
