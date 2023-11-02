package org.food.dao;

import org.food.api.repository.Dao;
import org.food.model.Meal;
import org.springframework.stereotype.Repository;

@Repository
public class MealRepositoryImpl extends AbstractDao implements Dao {

    public MealRepositoryImpl() {
        super(Meal.class);
    }
}