package com.example.demo.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.*;

@Repository("caseRepository")
public interface CaseRepository extends CrudRepository<Case, Integer> {
	
	List<Case> findByCaseType(String caseType);

	void deleteByCaseId(int caseid);

	void deleteByUserId(int userid);
	
	Case findByCaseId(int caseId);

	List<Case> findByUser(User user);
	
	List<Case> findAll();
}
