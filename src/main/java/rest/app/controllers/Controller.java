package rest.app.controllers;

import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import rest.app.model.EntryBook;
import rest.app.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {
    private  final UserService userService = new UserService();

    // Создание нового пользователя
    @PostMapping(value = "/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Добавление записи в книжку пользователя
    @PostMapping(value = "/users/{id}/book")
    public ResponseEntity<?> addEntryInBook(@PathVariable(name = "id") int id, @RequestBody EntryBook entry) {
        userService.addEntryInBook(id, entry);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") int id, @RequestBody User user) {
        final boolean res = userService.updateUser(id, user);
        if (res)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "users/{id}/book")
    public ResponseEntity<?> updateUserBook(@RequestBody EntryBook entry, @PathVariable(name = "id") int id) {
        boolean res = userService.updateEntryInBook(id, entry);
        if (res)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>("Error! Not found!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>>getUsers() {
        final List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.FOUND);
    }

    @GetMapping(value = "/users/{id}/book")
    public ResponseEntity<Map<String, String>> getUserBook(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(userService.getUserBook(id), HttpStatus.FOUND);
    }

    @DeleteMapping(value = "users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") int id) {
        boolean res = userService.deleteUser(id);
        if(res)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
