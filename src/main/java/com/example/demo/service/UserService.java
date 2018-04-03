package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Case;
import com.example.demo.model.IpcCode;
import com.example.demo.model.LawyerInfo;
import com.example.demo.model.User;

public interface UserService {

	public User findUserByEmail(String email);

	public LawyerInfo findLawyerByEmail(String email);

	public Case findcaseByCaseid(int caseId);
	
	public User findUserById(int id);
	
	public IpcCode findBySection(String section);
	
	public User findByIds(int id);
	
	public LawyerInfo findUser(User user);
	
	public User findUserByResetToken(String token);

	public void saveUser(User user);
	
	public void updateUser(User user,int user_id);
	
	public void delete(User user);

	public void deleteUserById(int id);
	
	public void saveLawyer(LawyerInfo lawyer, int user_id);

	public void saveIpcCode(IpcCode ipc);

	public void saveCase(Case cases, int user_id);

	public void deleteipcBySection(String section);

	public void deletecaseByCaseId(int caseId);
	
	public List<User> find();
	
	public List<IpcCode> findAllIpc();
	
	public List<IpcCode> findByKeyword(String keyword);
	
	public List <LawyerInfo> findByCourtAndType(String court, String type);
	
	public List<Case> findByUsers(User userid);
	
	public List<String> findByTypes(String type);
	
	public List<Case> findAllCases();
	
	public List<Case> findByType(String casetype);
	
	
}