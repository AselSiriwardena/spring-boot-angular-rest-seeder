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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BookController {

    @Autowired
    UserRepository userRepository;

    // Get All
    @PostMapping("/users")
    public List<User> getAllNotes() {
        System.out.println("req");
        return userRepository.findAll();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = "application/json")
    public void findUsers(HttpServletResponse response) throws IOException {
        List<User> users = userRepository.findAll();
        WriteCsvToResponse.writeUsers(response.getWriter(), users);
        System.out.println(users);
    }

    // Get a Single User
    @GetMapping("/books/{id}")
    public User getNoteById(@PathVariable(value = "id") int userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    // Update a Note
    @PutMapping("/books/{id}")
    public User updateNote(@PathVariable(value = "id") int id,
                           @Valid @RequestBody User userDetails) throws UserNotFoundException {

        User user= userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setId(userDetails.getId());
        user.setName(user.getName());
        user.setPassword(user.getPassword());

        User updatedUser = userRepository.save(user);

        return updatedUser;
    }

    // Delete a Note
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") int id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }
}