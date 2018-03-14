package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.LawyerInfo;
@Repository("lawyerRepository")
public interface LawyerRepository extends JpaRepository<LawyerInfo, Integer> {
 LawyerInfo findByEmail (String email);
 ArrayList<LawyerInfo>  findByCourt(String court);
 ArrayList<LawyerInfo> findByType(String type);
 ArrayList<LawyerInfo> findByCourtAndType(String court,String type);
}
