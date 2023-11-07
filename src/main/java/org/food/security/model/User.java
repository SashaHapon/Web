package org.food.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "user", schema = "mydb")
@Data
@AllArgsConstructor

public class User {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private Set<Role> roles;
}
