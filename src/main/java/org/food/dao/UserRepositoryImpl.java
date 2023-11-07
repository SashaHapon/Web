package org.food.dao;

import org.food.api.repository.Dao;
import org.food.security.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends AbstractDao implements Dao {
    public UserRepositoryImpl(){super(User.class);}

    public User getByLogin(String login){
        return entityManager.find(User.class, login);
    }
}
