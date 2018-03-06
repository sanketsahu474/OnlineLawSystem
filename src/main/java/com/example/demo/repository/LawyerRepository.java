package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.LawyerInfo;

public interface LawyerRepository extends JpaRepository<LawyerInfo, Integer> {

}
