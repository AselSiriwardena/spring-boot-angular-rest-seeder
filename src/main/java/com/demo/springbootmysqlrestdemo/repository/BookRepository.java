package com.demo.springbootmysqlrestdemo.repository;

import com.demo.springbootmysqlrestdemo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}