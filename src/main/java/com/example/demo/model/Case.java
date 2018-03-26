package com.example.demo.model;

//import java.util.Set;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;*/
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "cases")
public class Cases {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "case_id")
	private int caseId;
	@Column(name="case_description")
	@NotEmpty(message="please input the case description")
	private String description;
	/*@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_case", joinColumns= @JoinColumn (name= "case_id"),inverseJoinColumns = @JoinColumn(name="user_id"))
	private Set<User>users;*/
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
/*	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
*/
	
}

