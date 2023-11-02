package org.food.dao;

import org.food.api.repository.Dao;
import org.food.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl extends AbstractDao implements Dao {

    public OrderRepositoryImpl() {
        super(Order.class);
    }
}
