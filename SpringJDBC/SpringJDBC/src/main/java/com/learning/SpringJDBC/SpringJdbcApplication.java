package com.learning.SpringJDBC;

import com.learning.SpringJDBC.model.Student;
import com.learning.SpringJDBC.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJdbcApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringJdbcApplication.class, args);
        Student student = context.getBean(Student.class);
        student.setRollNo(1);
        student.setName("Kartikeye");
        student.setMarks(100);

        StudentService service = context.getBean(StudentService.class);
        service.addStudent(student);

        List<Student> students = service.getStudents();
        System.out.println(students);
    }

}
