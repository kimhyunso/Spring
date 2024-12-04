package com.spring.board.vo;

public class CommandVo {
	private String codeType;
	private String codeID;
	private String codeName;
	private String creator;
	private String modifier;
	
	public CommandVo() {
		setCodeType("menu");
	}

	public CommandVo(String codeType) {
		// TODO Auto-generated constructor stub
		setCodeType(codeType);
	}
	
	public String getCodeType() {
		return codeType;
	}
	
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	
	public String getCodeID() {
		return codeID;
	}
	public void setCodeID(String codeID) {
		this.codeID = codeID;
	}
	
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
	public String getCreator() {
		return creator;
	}
	
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

}
