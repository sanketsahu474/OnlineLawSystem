package com.example.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "ipc_codes")
public class IpcCodes {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Sequence_no")
	private int Sequenceno;
	@Column(name = "section")
	@NotEmpty(message = "*Please provide the section Id")
	private String section;

	@Column(name="keyword")
	@NotEmpty(message="*please provide the keyword")
	private String keyword;
	@Column(name="code_name")
	@NotEmpty(message="*please provide the code name")
	private String codeName;
	@Column(name="code_description")
	@NotEmpty(message="*please enter the description")
	private String codeDescription;
	
	public int getSequenceno() {
		return Sequenceno;
	}
	public void setSequenceno(int sequenceno) {
		this.Sequenceno = sequenceno;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCodeDescription() {
		return codeDescription;
	}
	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}

}

