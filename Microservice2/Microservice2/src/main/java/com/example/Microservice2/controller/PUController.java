package com.example.Microservice2.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Microservice2.feign.Microservice1;
import com.example.Microservice2.model.PUModel;
import com.example.Microservice2.service.PUService;


@RestController
public class PUController {

	@Autowired
	PUService userService;
	
	@Autowired
	Microservice1 microservice1;
	
	@PostMapping("/save")
	public void saveUser(@RequestBody PUModel user) {
		userService.saveUser(user);
		return;
	}
	@DeleteMapping("delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return "User deleted";

	}
	@PutMapping("/update/{id}")
	public void updateUser(@PathVariable("id") Long id, @RequestBody PUModel updatedUser) {
	    userService.updateUser(id, updatedUser);
	}

	@GetMapping("/getAll")
	public List<PUModel> getAllUsers(){
		return microservice1.getAllUsers();
	}
	@GetMapping("/get/{id}")
	public Optional<PUModel> getUserById(@PathVariable("id") Long id){
		return microservice1.getUserById(id);
	}
}
