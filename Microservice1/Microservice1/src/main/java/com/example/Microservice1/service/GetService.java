package com.example.Microservice1.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.Microservice1.model.GetModel;
import com.example.Microservice1.repository.GetRepository;

@Service
public class GetService {
	@Autowired
    GetRepository userRepository;
	
	public List<GetModel> getAllUsers() {
        return userRepository.findAll();
    }
	
	public Optional<GetModel> getUserById(Long id) {
        return userRepository.findById(id);
    }
	

}
