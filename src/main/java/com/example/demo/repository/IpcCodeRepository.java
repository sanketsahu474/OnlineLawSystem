package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.IpcCode;;

@Repository("ipccodeRepository")
public interface IpcCodeRepository extends JpaRepository<IpcCode, String> {
	
	IpcCode findBySection(String section);

	List<IpcCode> findByKeyword(String keyword);

	void deleteBySection(String section);
}
