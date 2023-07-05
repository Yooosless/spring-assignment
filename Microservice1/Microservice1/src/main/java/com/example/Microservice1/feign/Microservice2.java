package com.example.Microservice1.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Microservice1.model.GetModel;

@FeignClient(name="microservice2",url="localhost:8082")
public interface Microservice2 {

	
	@PostMapping("/save")
	public void saveUser(@RequestBody GetModel user);
	@DeleteMapping("delete/{id}")
	public String deleteUser(@PathVariable("id") Long id);
}
