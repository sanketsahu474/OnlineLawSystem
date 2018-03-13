package com.example.demo.service;

import com.example.demo.model.IpcCodes;
import com.example.demo.model.LawyerInfo;
import com.example.demo.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public LawyerInfo findLawyerByEmail(String email);
	public void saveUser(User user);
	public void saveLawyer(LawyerInfo lawyer);
	public IpcCodes findipcBySection(String section);
	public void saveIpcCode(IpcCodes ipc);
	public void delete (User user);
}