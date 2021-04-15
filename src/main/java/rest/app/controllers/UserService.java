package rest.app.controllers;

import rest.app.model.EntryBook;
import rest.app.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserService {
    private static final Map<Integer, User> USERS_REPOSITORY = new HashMap<>();

    private static final AtomicInteger USER_ID_GENERATOR = new AtomicInteger();

    public void createUser(User user) {
        final int id = USER_ID_GENERATOR.incrementAndGet();
        user.setId(id);
        USERS_REPOSITORY.put(id, user);
    }

    public List<User> getUsers() {
        return new ArrayList<>(USERS_REPOSITORY.values());
    }

    public User getUserById(int id) {
        return USERS_REPOSITORY.get(id);
    }

    public boolean updateUser(int id, User user) {
        if (!USERS_REPOSITORY.containsKey(id))
            return false;
        user.setId(id);
        USERS_REPOSITORY.put(id, user);
        return true;
    }

    public boolean deleteUser(int id) {
        if (!USERS_REPOSITORY.containsKey(id))
            return false;
        USERS_REPOSITORY.remove(id);
        return true;
    }

    public void addEntryInBook(int id, EntryBook entry) {
        User user = USERS_REPOSITORY.get(id);
        user.addEntryBook(entry.getName(), entry.getPhone());
    }

    public boolean updateEntryInBook(int id, EntryBook entry) {
        if (!USERS_REPOSITORY.containsKey(id))
            return false;
        User user = USERS_REPOSITORY.get(id);
        if (user.getPhoneBook().containsKey(entry.getName())) {
            user.addEntryBook(entry.getName(), entry.getPhone());
            return true;
        }
        return false;
    }

    public Map<String, String> getUserBook(int id) {
        User user = USERS_REPOSITORY.get(id);
        return user.getPhoneBook();
    }

}
