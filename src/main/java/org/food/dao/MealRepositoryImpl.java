package org.food.dao;

import org.food.api.repository.MealRepository;
import org.food.model.Meal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import java.util.List;

@Repository
public class MealRepositoryImpl extends AbstractDao<Meal> implements MealRepository {

    public MealRepositoryImpl() {
        super(Meal.class);
    }

}