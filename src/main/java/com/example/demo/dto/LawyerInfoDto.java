package com.example.demo.dto;

public class LawyerInfoDto {

	private int lawyer_id;
	private String fname;
	private String lname;
	private String email;
	private String phoneNo;
	private String address;
	private String court;
	private String type;
	private int experience;
	private int totalCases;
	private int won;
	private int loss;
	public LawyerInfoDto(int lawyer_id, String fname, String lname, String email, String phoneNo, String address,
			String court, String type, int experience, int totalCases, int won, int loss) {
		super();
		this.lawyer_id = lawyer_id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.court = court;
		this.type = type;
		this.experience = experience;
		this.totalCases = totalCases;
		this.won = won;
		this.loss = loss;
	}
	public int getLawyer_id() {
		return lawyer_id;
	}
	public void setLawyer_id(int lawyer_id) {
		this.lawyer_id = lawyer_id;
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
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}
	public int getWon() {
		return won;
	}
	public void setWon(int won) {
		this.won = won;
	}
	public int getLoss() {
		return loss;
	}
	public void setLoss(int loss) {
		this.loss = loss;
	}
}
