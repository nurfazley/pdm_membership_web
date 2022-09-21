package com.pdm.membershipweb.model.lookup;

public enum State {

	PAHANG("PAHANG"),
	KELANTAN("KELANTAN"),
	TERENGGANU("TERENGGANU"),
	JOHOR("JOHOR"),
	NEGERI_SEMBILAN("NEGERI SEMBILAN"),
	MELAKA("MELAKA"),
	SELANGOR("SELANGOR"),
	PERAK("PERAK"),
	PULAU_PINANG("PULAU PINANG"),
	KEDAH("KEDAH"),
	PERLIS("PERLIS"),
	SABAH("SABAH"),
	SARAWAK("SARAWAK");

	private String textString;
	
	
	State(String string) {
		textString = string;
	}

	public String getTextString() {
		return textString;
	}

	public void setTextString(String textString) {
		this.textString = textString;
	}
}
