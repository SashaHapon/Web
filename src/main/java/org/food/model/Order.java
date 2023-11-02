package org.food.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order", schema = "mydb")
@Data
public class Order {
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Meal.class)
    private List<Meal> meals = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Account.class)
    private Account account;
    @Column(name = "account_Id")
    private String accountId;
    private double orderSum;
    private int cookingTimeSum;

    @Column(name = "meal_Id")
    private String mealId;

    public Order(){};
    public Order(String id){
        this.id = id;
    }




}
