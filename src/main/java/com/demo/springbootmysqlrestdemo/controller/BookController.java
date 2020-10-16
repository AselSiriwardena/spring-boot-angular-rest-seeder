package com.demo.springbootmysqlrestdemo.controller;

import com.demo.springbootmysqlrestdemo.exception.UserNotFoundException;
import com.demo.springbootmysqlrestdemo.models.User;
import com.demo.springbootmysqlrestdemo.repository.UserRepository;
import com.demo.springbootmysqlrestdemo.util.WriteCsvToResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class BookController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = "application/json")
    public void findUsers(HttpServletResponse response) throws IOException {
        List<User> users = userRepository.findAll();
        WriteCsvToResponse.writeUsers(response.getWriter(), users);
        System.out.println(users);
    }

    // Get a Single User
    @RequestMapping(value = "/getUser", method = RequestMethod.POST, produces = "application/json")
    public User getNoteById(@RequestBody User user) throws UserNotFoundException {
        return userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException(user.getId()));
    }

    // Update a User
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = "application/json")
    public User updateNote(@PathVariable(value = "id") int id,
                           @Valid @RequestBody User userDetails) throws UserNotFoundException {

        User user= userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setId(userDetails.getId());
        user.setName(user.getName());
        user.setPassword(user.getPassword());

        return userRepository.save(user);
    }

    // Delete a User
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") int id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    // Delete all User
    @RequestMapping(value = "/deleteUsers", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> deleteUsers() {
        userRepository.	deleteAllInBatch();
        return ResponseEntity.ok().build();
    }
}