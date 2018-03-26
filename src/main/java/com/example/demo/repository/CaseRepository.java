package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.*;
@Repository("caseRepository")
public interface CasesRepository extends CrudRepository<Cases, Integer> {

}
