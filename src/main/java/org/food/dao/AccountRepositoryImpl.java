package org.food.dao;

import org.food.api.repository.GenericDao;
import org.food.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl extends AbstractDao implements GenericDao {

    public AccountRepositoryImpl() {
        super(Account.class);
    }
}
