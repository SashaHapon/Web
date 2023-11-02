package org.food.model;


import jakarta.persistence.*;
import lombok.Data;

@Table(name = "account", schema = "mydb")
@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Double money;

    @Column(name = "phone_number")
    private String phoneNumber;

    public Account(){};
    public Account(int id){
        this.id = id;
    };

    public Account(String accountName, double moneyOnCard, String phoneNumber){
        this.name = accountName;
        this.money = moneyOnCard;
        this.phoneNumber = phoneNumber;
    }
}
