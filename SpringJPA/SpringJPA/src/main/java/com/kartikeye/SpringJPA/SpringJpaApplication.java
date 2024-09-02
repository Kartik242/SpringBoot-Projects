package com.kartikeye.SpringJPA;

import com.kartikeye.SpringJPA.model.Student;
import com.kartikeye.SpringJPA.repo.StudentRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJpaApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringJpaApplication.class, args);

        //Object for repository
        StudentRepo repo = context.getBean(StudentRepo.class);

        Student s1 = context.getBean(Student.class);
        Student s2 = context.getBean(Student.class);
        Student s3 = context.getBean(Student.class);

        s1.setRollNo(1);
        s1.setName("Kartikeye");
        s1.setMarks(100);

        s2.setRollNo(2);
        s2.setName("Kartik");
        s2.setMarks(80);

        s3.setRollNo(3);
        s3.setName("Suraj");
        s3.setMarks(60);

        //using JPA
        repo.save(s1);
        repo.save(s2);
        repo.save(s3);


    }

}
