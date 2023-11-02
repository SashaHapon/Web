package org.food.api.repository;

import org.food.model.Meal;

import java.util.List;


public interface MealRepository extends Dao{

    Meal addMeal(Meal meal);

    Meal getMeal(String id);

    void deleteMealById(String id);

    List<Meal> getAllMeal();

    void update(Meal meal);
}
