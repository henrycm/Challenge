package com.jhcm.appdirect.integration.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jhcm.appdirect.integration.xml.types.ErrorCode;

@XmlRootElement(name = "result")
public class Result implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean success;
	private boolean asynchronous = false;
	private ErrorCode errorCode;
	private String message;
	private String accountIdentifier;
	private String userIdentifier;
	private String id;

	public Result() {
	}

	public Result(boolean success, String message) {
		this(success, null, message);
	}

	public Result(boolean success, ErrorCode errorCode, String message) {
		super();
		this.success = success;
		this.errorCode = errorCode;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isAsynchronous() {
		return asynchronous;
	}

	public void setAsynchronous(boolean asynchronous) {
		this.asynchronous = asynchronous;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	public String getUserIdentifier() {
		return userIdentifier;
	}

	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
