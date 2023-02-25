package home.demo;

public class Owner {
    long id;
    private String name;
    private String passport;
    private String address;
    private String birthday;

    public Owner(long id, String name, String passport, String address, String birthday) {
        this.id = id;
        this.name = name;
        this.passport = passport;
        this.address = address;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passport='" + passport + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
