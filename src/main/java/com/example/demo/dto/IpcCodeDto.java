package com.example.demo.dto;

public class IpcCodeDto {

	private String section;
	private String keyword;
	private String codeName;
	private String codeDescription;
	public IpcCodeDto(String section, String keyword, String codeName, String codeDescription) {
		super();
		this.section = section;
		this.keyword = keyword;
		this.codeName = codeName;
		this.codeDescription = codeDescription;
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

