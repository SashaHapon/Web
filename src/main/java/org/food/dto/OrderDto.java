package org.food.dto;

import org.food.model.Meal;
import lombok.*;

import java.util.List;

@Data
public class OrderDto {

    int id;

    public List<Meal> meals;

    public String accountId;

    AccountDto accountDto;

    MealDto mealDto;

}
