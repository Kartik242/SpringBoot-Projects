package com.kartikeye.SpringSecurity.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id

    private  int id;
    private String username;
    private String password;
}
