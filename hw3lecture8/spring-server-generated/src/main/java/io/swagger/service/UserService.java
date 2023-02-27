package io.swagger.service;

import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public void createOrUpdateUser(User body) {
        repo.saveAndFlush(body);
    }

    public void deleteUser(Long userId) {
        repo.deleteById(userId);
    }

    public User getById(Long userId) {
        return repo.findById(userId).orElse(null);
    }
}
