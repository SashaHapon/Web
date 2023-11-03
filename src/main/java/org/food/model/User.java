package org.food.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user", schema = "mydb")
@Data
public class User {
    @Id
    @GeneratedValue
    private String id;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private Integer age;
    private String email;
    private String roles;

}