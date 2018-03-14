package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.IpcCodes;;
@Repository("ipccodeRepository")
public interface IpcCodesRepository extends JpaRepository <IpcCodes, String> {
	 IpcCodes findBySection(String section);
	ArrayList<IpcCodes>  findByKeyword(String keyword);
}
