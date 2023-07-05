package com.example.Microservice1.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import com.example.Microservice1.model.GetModel;

@Service
public class Producer {
	@Autowired
	Gson gson;
	
	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;
	
	public CompletableFuture<SendResult<String, String>> sendMsgToTopic(GetModel message) {
		return kafkaTemplate.send("testTopic",gson.toJson(message));
	}
}
