package boot.springboot.controller;

import boot.springboot.model.Role;
import boot.springboot.model.User;
import boot.springboot.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception_handling.NoSuchUserException;
import exception_handling.UserIncorrectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@PropertySource("classpath:application.properties")
@RequestMapping ("/api")
public class RESTController {

    @Autowired
    private UserService userService;

    @GetMapping ("/users")
    public List<User> showAllUsers(){
        List<User> list = userService.getAllUsers();
        return list;
    }

    @GetMapping (value = "/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(Integer.parseInt(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping ("/users")
    public User addUser (@RequestBody User user) {
        if (user == null){
            throw new NoSuchUserException("User is null");
        }
        user.setEnabled(1);
        userService.saveUser(user);
        return user;
    }

    @PutMapping ("/users/{id}")
    public User updateUser (@PathVariable String id, @RequestBody String userString) {
        User user = null;
        try {
            user = new ObjectMapper().readValue(userString, User.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (user == null){
            throw new NoSuchUserException("User is null");
        }
        user.setId(Integer.parseInt(id));
        user.setEnabled(1);
        userService.saveUser(user);
        return user;
    }

    @DeleteMapping ("/users/{data}")
    public String deleteUser(@PathVariable String data){
        int id;
        try {
            id = Integer.parseInt(data);
        } catch (Exception e){
            id = userService.getUserByUserName(data).getId();
        }
        if (id == 0){
            throw new NoSuchUserException("User not found");
        }
        userService.removeUserById(id);
        return "User with ID = " + id + " was deleted";
    }

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(NoSuchUserException exception){
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(Exception exception){
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}
