package com.example.Microservice1.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Microservice1.feign.Microservice2;
import com.example.Microservice1.model.GetModel;
import com.example.Microservice1.service.GetService;
import com.example.Microservice1.service.Producer;

@RestController
public class GetController {
	
	@Autowired
	GetService userService;
	@Autowired
	Producer producer;
	
	@Autowired
	Microservice2 microservice2;
	private final Logger logger= LoggerFactory.getLogger(GetController.class);

	@GetMapping("/getAll")

	public List<GetModel> getAllUsers(){
		return userService.getAllUsers();
	}
	@GetMapping("/get/{id}")
	@Cacheable(value="get",key="#id")
	public Optional<GetModel> getUserById(@PathVariable("id") Long id){
		System.out.println("Employee fetching from database:: "+id);
		return userService.getUserById(id);
	}
	@PostMapping("/save")
	public String saveUser(@RequestBody GetModel user) {
		microservice2.saveUser(user);
		return "User Registered";
	}
	@PostMapping("/saveKafka")
	public void getMessageFromClient(@RequestBody GetModel message) {
		producer.sendMsgToTopic(message);
	}

	@DeleteMapping("delete/{id}")
	@CacheEvict(value="delete",allEntries = true)
	public String deleteUser(@PathVariable("id") Long id) {
		microservice2.deleteUser(id);
		return "User deleted";

	}
}
