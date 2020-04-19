package com.demo.springbootmysqlrestdemo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String book_name;
    @NotBlank
    private String isbn;
    public Book(){
        super();
    }
    public Book(Long id, String book_name, String isbn) {
        super();
        this.id = id;
        this.book_name = book_name;
        this.isbn=isbn;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBook_name() {
        return book_name;
    }
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("id=").append(id);
        sb.append(", book_name='").append(book_name).append('\'');
        sb.append(", isbn=").append(isbn);
        sb.append('}');
        return sb.toString();
    }
}
