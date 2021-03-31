package com.training.model;

public enum AllergyType {

	DRUG("drug"), FOOD("food"), INSECT("insect"), LATEX("latex");
	
	private String value;
	
	private AllergyType(final String value) {
		this.value = value;
	}

	public String getRole() {
		return value;
	}


}
