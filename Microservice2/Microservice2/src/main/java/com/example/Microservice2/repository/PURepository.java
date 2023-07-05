package com.example.Microservice2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Microservice2.model.PUModel;

public interface PURepository extends JpaRepository<PUModel, Long> {


}
