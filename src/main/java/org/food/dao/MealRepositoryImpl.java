package org.food.dao;

import org.food.api.repository.GenericDao;
import org.food.model.Meal;
import org.springframework.stereotype.Repository;

@Repository
public class MealRepositoryImpl extends AbstractDao implements GenericDao {

    public MealRepositoryImpl() {
        super(Meal.class);
    }
}