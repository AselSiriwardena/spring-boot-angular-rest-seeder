package com.demo.springbootmysqlrestdemo.controller;

import com.demo.springbootmysqlrestdemo.exception.BookNotFoundException;
import com.demo.springbootmysqlrestdemo.models.Book;
import com.demo.springbootmysqlrestdemo.repository.BookRepository;
import com.demo.springbootmysqlrestdemo.util.WriteCsvToResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    // Get All Notes
    @PostMapping("/books")
    public List<Book> getAllNotes() {
        return bookRepository.findAll();
    }

    @RequestMapping(value = "/books-csv", produces = "text/csv")
    public void findCities(HttpServletResponse response) throws IOException {

        List<Book> cities = bookRepository.findAll();

        WriteCsvToResponse.writeCities(response.getWriter(), cities);
    }

    // Get a Single Note
    @GetMapping("/books/{id}")
    public Book getNoteById(@PathVariable(value = "id") Long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    // Update a Note
    @PutMapping("/books/{id}")
    public Book updateNote(@PathVariable(value = "id") Long bookId,
                           @Valid @RequestBody Book bookDetails) throws BookNotFoundException {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        book.setBook_name(bookDetails.getBook_name());
        book.setIsbn(bookDetails.getIsbn());

        Book updatedBook = bookRepository.save(book);

        return updatedBook;
    }

    // Delete a Note
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        bookRepository.delete(book);

        return ResponseEntity.ok().build();
    }
}