package com.pdm.membershipweb.model.lookup;

public enum Country {

	MALAYSIA("MALAYSIA"),
	SINGAPORE("SINGAPORE"),
	THAILAND("THAILAND"),
	INDONESIA("INDONESIA");
	
	private String textString;
	

	Country(String string) {
		textString = string;
	}

	public String getTextString() {
		return textString;
	}

	public void setTextString(String textString) {
		this.textString = textString;
	}
}
