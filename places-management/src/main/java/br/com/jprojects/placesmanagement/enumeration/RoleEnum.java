package br.com.jprojects.placesmanagement.enumeration;

public enum RoleEnum {
	
	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_USER("ROLE_USER");
	
	private String value;
	
	private RoleEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

}
