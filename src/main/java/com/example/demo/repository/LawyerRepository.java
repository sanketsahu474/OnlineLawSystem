package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.LawyerInfo;
import com.example.demo.model.User;

@Repository("lawyerRepository")
public interface LawyerRepository extends JpaRepository<LawyerInfo, Integer> {
	
	LawyerInfo findByEmail(String email);

	LawyerInfo findByUser(User user);

	List<LawyerInfo> findByCourt(String court);

	List<LawyerInfo> findByCourtAndType(String court, String type);

	List<LawyerInfo> findByType(String type);
	
	void deleteByUserId(int userid);
}
