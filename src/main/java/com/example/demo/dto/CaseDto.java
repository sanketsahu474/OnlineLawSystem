package com.example.demo.dto;

public class CaseDto {

	private int caseId;
	private String description;
	public CaseDto(int caseId, String description) {
		super();
		this.caseId = caseId;
		this.description = description;
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
}