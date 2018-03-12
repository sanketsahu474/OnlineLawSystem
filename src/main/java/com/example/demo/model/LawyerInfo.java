package com.example.demo.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
//import javax.persistence.OneToOne;*/
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "lawyer_info")
public class LawyerInfo {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lawyer_id")
	private int lawyerid;
	@Column(name="first_name")
	@NotEmpty(message="*please input your first name")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "*please enter last name correctly")
	private String fname;
	@Column(name="last_name")
	@NotEmpty(message="*please input your last name")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "*please enter last name correctly")
	private String lname;
	@Column(name = "emailid")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	//@Pattern(regexp="/^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$/",message = "*please enter email id correctly")
	private String email;
	@Column(name="phone_no")
	@NotEmpty(message="please provide your phone no")
	@Pattern(regexp="^\\d{10}$",message="*please enter phone no in correct format")
	private String phoneNo;
	@Column(name= "office_address")
	@NotEmpty(message="please provide your office address")
	private String address;
	@Column(name= "court")
	@NotEmpty(message="please select court where you practice law")
	private String court;
	@Column(name= "type")
	@NotEmpty(message="please select your type")
	private String type;
	@Column(name = "experience")
	@NotEmpty(message="please input experince")
	@Pattern(regexp="^\\d{2}$",message ="*insert your experience in format of 2 digit number")
	private String experience;
	@Column(name = "total_cases")
	@Pattern(regexp="^\\d{3}$",message ="*insert your experience in format of 3 digit number")
	@NotEmpty(message="please input no. of total cases")
	private String totalCases;
	
	/*@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_lawyer", joinColumns= @JoinColumn (name= "lawyer_id"),inverseJoinColumns = @JoinColumn(name="user_id"))
	private Set<User>users;*/
	public int getLawyerid() {
		return lawyerid;
	}
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(String totalCases) {
		this.totalCases = totalCases;
	}

	/*public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
*/	
	
}
