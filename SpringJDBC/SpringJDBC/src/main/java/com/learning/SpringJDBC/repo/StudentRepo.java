package com.learning.SpringJDBC.repo;

import com.learning.SpringJDBC.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepo {

    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Student s) {
        //System.out.println("student added");
        String sql = "insert into student (rollno,name,marks) values(?,?,?)";
        int rows = jdbc.update(sql, s.getRollNo(), s.getName(), s.getMarks());
        System.out.println(rows + " gets affected");
    }

    public List<Student> findAll() {
//        List<Student> students = new ArrayList<>();
//        return students;

        String sql = "select * from student";
        // RowMapper<Student> mapper =

        return jdbc.query(sql, (ResultSet rs, int rowNum) -> {
            Student s = new Student();
            s.setRollNo(rs.getInt("rollno"));
            s.setName(rs.getString("name"));
            s.setMarks(rs.getInt("marks"));

            return s;

        });

    }
}
