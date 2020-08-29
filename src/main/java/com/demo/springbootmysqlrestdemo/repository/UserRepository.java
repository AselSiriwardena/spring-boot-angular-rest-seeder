package com.demo.springbootmysqlrestdemo.repository;

import com.demo.springbootmysqlrestdemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}