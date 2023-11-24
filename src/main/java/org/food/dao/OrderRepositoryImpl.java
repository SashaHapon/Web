package org.food.dao;

import org.food.api.repository.GenericDao;
import org.food.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl extends AbstractDao implements GenericDao {

    public OrderRepositoryImpl() {
        super(Order.class);
    }
}
