package com.example.Microservice1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	private final Logger logger= LoggerFactory.getLogger(Consumer.class);
	
	@KafkaListener(topics="testTopic",groupId="groupId")
	public void consume(String message) {
		logger.info(String.format("Consumer message: %s", message));
	}
}
