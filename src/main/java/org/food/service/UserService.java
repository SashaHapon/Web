package org.food.service;

import org.food.dao.UserRepositoryImpl;
import org.food.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepositoryImpl repository;

    public UserService(UserRepositoryImpl repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return null;
    }

    public User getByLogin(String login) {
        return this.repository.getByLogin(login);
    }
}
