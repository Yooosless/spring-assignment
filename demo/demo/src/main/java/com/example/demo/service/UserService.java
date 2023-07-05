package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class UserService {

    private static final String CIRCUIT_BREAKER = "userServiceCircuitBreaker";
    private static final String RETRY = "userServiceRetry";

    @Autowired
    UserRepository userRepository;

    @CircuitBreaker(name = CIRCUIT_BREAKER, fallbackMethod = "fallbackSaveUser")
    @Retry(name = RETRY, fallbackMethod = "fallbackSaveUser")
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @CircuitBreaker(name = CIRCUIT_BREAKER, fallbackMethod = "fallbackGetUserById")
    @Retry(name = RETRY, fallbackMethod = "fallbackGetUserById")
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @CircuitBreaker(name = CIRCUIT_BREAKER, fallbackMethod = "fallbackDeleteUser")
    @Retry(name = RETRY, fallbackMethod = "fallbackDeleteUser")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public String fallbackSaveUser(User user, Throwable exception) {
        return "Fallback response for User Registration";
    }

    public Optional<User> fallbackGetUserById(Long id, Throwable exception) {
       
        Logger logger = Logger.getLogger(getClass().getName());
        logger.warning("Exception occurred while retrieving user by ID: " + id);
        logger.warning(exception.getMessage());

        return Optional.empty();
    }

    public void fallbackDeleteUser(Long id, Throwable exception) {
        Logger logger = Logger.getLogger(getClass().getName());
        logger.warning("Exception occurred while deleting user with ID: " + id);
        logger.warning(exception.getMessage());
     
        logger.warning("Unable to delete user with ID: " + id + ". Fallback logic executed.");
        return;
    }
}
