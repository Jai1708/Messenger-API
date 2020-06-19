package org.jai.Messenger.model;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement 
public class ErrorMessage {

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int geterrorCode() {
		return errorCode;
	}

	public void seterrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	private String errorMessage;
	private int errorCode;
	private String documentation;

	public ErrorMessage(String errorMessage, int errorCode, String documentation) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.documentation = documentation;
	}

	public ErrorMessage() {
	}
}
