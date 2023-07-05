package com.example.Microservice2.feign;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.Microservice2.model.PUModel;

@FeignClient(name="microservice1",url="localhost:8081")
public interface Microservice1 {

	@GetMapping("/getAll")
	public List<PUModel> getAllUsers();
	@GetMapping("/get/{id}")
	public Optional<PUModel> getUserById(@PathVariable("id") Long id);
} 
