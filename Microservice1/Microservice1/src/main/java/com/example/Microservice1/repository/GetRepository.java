package com.example.Microservice1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Microservice1.model.GetModel;


public interface GetRepository extends JpaRepository<GetModel, Long> {

}
