package com.example.demo.dto;

public class UserDto {

	private int id;
	private String email;
	private String fnmae;
	private String lname;
	private String password;
	public UserDto(int id, String email, String fnmae, String lname, String password) {
		super();
		this.id = id;
		this.email = email;
		this.fnmae = fnmae;
		this.lname = lname;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFnmae() {
		return fnmae;
	}
	public void setFnmae(String fnmae) {
		this.fnmae = fnmae;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
