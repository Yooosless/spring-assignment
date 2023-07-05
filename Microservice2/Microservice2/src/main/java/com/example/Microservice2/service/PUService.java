package com.example.Microservice2.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Microservice2.model.PUModel;
import com.example.Microservice2.repository.PURepository;

@Service
public class PUService {

	 @Autowired
	 PURepository userRepository;
	
	 public void saveUser(PUModel user) {
	        userRepository.save(user);
	    }
	 
	 public void deleteUser(Long id) {
	        userRepository.deleteById(id);
	    }
	 public void updateUser(Long id, PUModel updatedUser) {
		    Optional<PUModel> optionalUser = userRepository.findById(id);
		    if (optionalUser.isPresent()) {
		        PUModel user = optionalUser.get();
		        user.setName(updatedUser.getName());
		        user.setEmail(updatedUser.getEmail());
		        userRepository.save(user);
		    } else {
		        System.out.println("User not found with ID: " + id);
		    }
		}

}
