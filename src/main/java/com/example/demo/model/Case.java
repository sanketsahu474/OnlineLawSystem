package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "cases")
public class Case {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "case_id")
	private int caseId;

	@Column(name = "case_type")
	@NotEmpty(message = "*please input the case type")
	private String caseType;

	@Column(name = "description")
	@NotEmpty(message = "*please input the case description")
	private String description;

	@Email(message = "*Please provide a valid Email")
	private String email;

	@Column(name = "contact")
	@NotEmpty(message = "please provide contact details")
	@Pattern(regexp = "^\\d{10}$", message = "*please enter phone no in 10 digit format")
	private String contact;

	@OneToOne(cascade =CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}
