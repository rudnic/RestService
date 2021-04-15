package rest.app.model;

public class EntryBook {
    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public EntryBook(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}