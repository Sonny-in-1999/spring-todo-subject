package com.example.springtodosubject;

import com.example.springtodosubject.db.DBConnectionTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringTodoSubjectApplication {

    public static void main(String[] args)  {
        DBConnectionTest.start();
        SpringApplication.run(SpringTodoSubjectApplication.class, args);
    }

}
