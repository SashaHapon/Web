package org.food.dao;

import org.food.api.repository.Dao;
import org.food.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl extends AbstractDao implements Dao {

    public AccountRepositoryImpl() {
        super(Account.class);
    }
}
