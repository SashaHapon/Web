package org.food.api.service;

import org.food.dto.MealDto;

import java.util.List;

public interface MealService {

    List<MealDto> getAll();

    void addMeal(MealDto mealDto);

    MealDto getMeal(MealDto mealDto);

    void deleteMealById(MealDto mealDto);

    void update(MealDto mealDto);
}
