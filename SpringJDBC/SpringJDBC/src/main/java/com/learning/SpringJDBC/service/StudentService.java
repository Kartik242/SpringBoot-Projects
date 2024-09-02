package com.learning.SpringJDBC.service;

import com.learning.SpringJDBC.model.Student;
import com.learning.SpringJDBC.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {


    private StudentRepo studentRepo;

    public StudentRepo getStudentRepo() {
        return studentRepo;
    }

    @Autowired
    public void setStudentRepo(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public void addStudent(Student student) {

        studentRepo.save(student);
    }

    public List<Student> getStudents() {
        return studentRepo.findAll();
    }
}
