package com.example.demo.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.IpcCodes;
import com.example.demo.model.LawyerInfo;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.IpcCodesRepository;
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
	private IpcCodesRepository ipccodeRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        //some changes are done here
        Role userRole = roleRepository.findByRole(user.getRole());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	
	@Override
	public LawyerInfo findLawyerByEmail(String email) {
		return lawyerRepository.findByEmail(email);
	}
	
	@Override
	public IpcCodes findipcBySection(String section) {
		return ipccodeRepository.findBySection(section);
		}
	
	public void saveLawyer(LawyerInfo lawyer) {
		lawyer.setFname(lawyer.getFname());
		lawyer.setLname(lawyer.getLname());
		lawyer.setEmail(lawyer.getEmail());
		lawyer.setPhoneNo(lawyer.getPhoneNo());
		lawyer.setAddress(lawyer.getAddress());
		lawyer.setCourt(lawyer.getCourt());
		lawyer.setType(lawyer.getType());
		lawyer.setExperience(lawyer.getExperience());
		lawyer.setTotalCases(lawyer.getTotalCases());
		
		lawyerRepository.save(lawyer);
	}
	public void saveIpcCode(IpcCodes ipc) {
		ipc.setSection(ipc.getSection());
		ipc.setKeyword(ipc.getKeyword());
		ipc.setCodeName(ipc.getCodeName());
		ipc.setCodeDescription(ipc.getCodeDescription());
		ipccodeRepository.save(ipc);
	}
}
