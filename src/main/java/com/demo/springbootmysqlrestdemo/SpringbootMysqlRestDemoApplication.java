package com.demo.springbootmysqlrestdemo;

import com.demo.springbootmysqlrestdemo.util.FrontendApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpringbootMysqlRestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMysqlRestDemoApplication.class, args);
		try {
			FrontendApplication.runApp();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
