package org.food.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order", schema = "mydb")
@Getter
@Setter
@NamedEntityGraph(name = "order_entity_graph", attributeNodes = {
        @NamedAttributeNode("account"),
        @NamedAttributeNode("meals")
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Meal.class)
    private List<Meal> meals = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Account.class)
    private Account account;

    private BigDecimal orderSum;

    private int cookingTimeSum;

    @Column(name = "meal_Id")
    private String mealId;
}
