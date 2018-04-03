package com.example.demo.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Case;
import com.example.demo.model.IpcCode;
import com.example.demo.model.LawyerInfo;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.CaseRepository;
import com.example.demo.repository.IpcCodeRepository;
import com.example.demo.repository.LawyerRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;


@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private LawyerRepository lawyerRepository;
	@Autowired
	private IpcCodeRepository ipccodeRepository;
	@Autowired
	private CaseRepository casesRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/* to check if anyone else is trying to use same email id */
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/* to save user info after registration */
	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole(user.getRole());
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	
	/* To update user info*/
	@Override
	public void updateUser(User user,int user_id) {
		user.setId(user_id);
		user.setLastName(user.getLastName());
		user.setName(user.getName());
		user.setEmail(user.getEmail());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole(user.getRole());
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	/* to check if token exist or not*/
	 @Override
	  public User findUserByResetToken(String resetToken) {
	    return userRepository.findByResetToken(resetToken);
	  }
	/* to check if lawyer given emailid is been used by any other person or not*/
	@Override
	public LawyerInfo findLawyerByEmail(String email) {
		return lawyerRepository.findByEmail(email);
	}
	/* to delete a user*/
	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	/* to find list of all users*/
	@Override
	public List<User> find()
	{
		return userRepository.findAll();
	}
	
	/*To find list of all ipc codes*/
	@Override
	public List<IpcCode> findAllIpc() {
		return ipccodeRepository.findAll();
		
	}
	
	/* to find a case based on case id and return the case */
	@Override
	public Case findcaseByCaseid(int caseId) {
		return casesRepository.findByCaseId(caseId);
	}
	
	/*to find user based on his id*/
	@Override
	public User findUserById(int id) {
		return userRepository.findById(id);
	}
	
	/* to find ipc Code based on it section no.*/
	@Override
	public IpcCode findBySection(String section) {
	 return	ipccodeRepository.findBySection(section);
	}
	
	/*to find ipc code based on its keywords*/
	@Override
	public List<IpcCode> findByKeyword(String keyword) {
		return ipccodeRepository.findByKeyword(keyword);
	}
	
	/* to find the list of lawyers on the basis of court they practice and what type of lawyers they are*/
	@Override
	public List <LawyerInfo> findByCourtAndType(String court, String type) {
	return	lawyerRepository.findByCourtAndType(court, type);
	}
	
	/*to find list of cases of particular user*/
	@Override
	public List<Case> findByUsers(User userid){
		
	return casesRepository.findByUser(userid);
	}
	
	/* to find the list of*/
	@Override
	public List<String> findByTypes(String type) {
		return lawyerRepository.findByType(type);
	}
	
	/* to find specific user based on his id*/
	@Override
	public User findByIds(int id) {
	return	userRepository.findById(id);
	}
	
	/*to find list of all the cases*/
	@Override
	public List<Case> findAllCases() {
		return casesRepository.findAll();
	}
	
	/* to find the list of cases based on its type*/
	@Override
	public List<Case> findByType(String casetype) {
	 return	casesRepository.findByCaseType(casetype);
	}
	
	/* to find particular lawyer info*/
	@Override
	public LawyerInfo findUser(User user) {
	return lawyerRepository.findByUser(user);
	}
	/* To save lawyer information */
	@Override
	public void saveLawyer(LawyerInfo lawyer, int user_id) {
		lawyer.setFname(lawyer.getFname());
		lawyer.setLname(lawyer.getLname());
		lawyer.setEmail(lawyer.getEmail());
		lawyer.setPhoneNo(lawyer.getPhoneNo());
		lawyer.setAddress(lawyer.getAddress());
		lawyer.setCourt(lawyer.getCourt());
		lawyer.setType(lawyer.getType());
		lawyer.setExperience(lawyer.getExperience());
		lawyer.setTotalCases(lawyer.getTotalCases());
		User userid = userRepository.findById(user_id);
		lawyer.setUser(userid);
		lawyerRepository.save(lawyer);
	}

	/* to save IPC code in database */
	@Override
	public void saveIpcCode(IpcCode ipc) {
		ipc.setSection(ipc.getSection());
		ipc.setKeyword(ipc.getKeyword());
		ipc.setCodeName(ipc.getCodeName());
		ipc.setCodeDescription(ipc.getCodeDescription());
		ipccodeRepository.save(ipc);
	}

	/* to save the case in database */
	@Override
	public void saveCase(Case cases, int user_id) {
		cases.setCaseId(cases.getCaseId());
		cases.setDescription(cases.getDescription());
		cases.setCaseType(cases.getCaseType());
		User userid = userRepository.findById(user_id);
		cases.setUser(userid);
		casesRepository.save(cases);
	}

	/* to delete any ipc code base on it section no. */
	@Transactional
	public void deleteipcBySection(String section) {
		ipccodeRepository.deleteBySection(section);
	}

	/* this function is for deleting the user based on the user id */
	@Transactional
	public void deleteUserById(int id) {
		casesRepository.deleteByUserId(id);
		lawyerRepository.deleteByUserId(id);
		userRepository.deleteById(id);
	}

	/* this function is for deleting the case based on the case id */
	@Transactional
	public void deletecaseByCaseId(int caseId) {
		casesRepository.deleteByCaseId(caseId);
	}

}
