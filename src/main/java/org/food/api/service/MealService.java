package org.food.api.service;

import org.food.dto.MealDto;

import java.util.List;

public interface MealService {

    List<MealDto> getAllMeals(int page, int size);

    MealDto addMeal(MealDto mealDto);

    MealDto getMeal(Integer id);

    void deleteMealById(Integer id);

    void update(MealDto mealDto);
}
