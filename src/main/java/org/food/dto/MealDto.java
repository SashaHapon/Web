package org.food.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealDto {
    int id;

    String name;

    Double price;

    int time;

    String date;
}

