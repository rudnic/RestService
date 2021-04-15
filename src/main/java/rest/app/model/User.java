package rest.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private Integer id;
    private String name;
    private final Map<String, String> phoneBook = new HashMap<>();

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getPhoneBook() {
        return phoneBook;
    }

    public void addEntryBook(String name, String  phone) {
        phoneBook.put(name, phone);
    }

}
