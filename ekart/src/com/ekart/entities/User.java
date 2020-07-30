package com.ekart.entities;

public class User {

	private String userId;
	private String name;
	private String password;
	private String emailId;
	private boolean isAdmin;
	
	public User(String userId, String name, String password, String emailId) {
		super();
		this.name = name;
		this.password = password;
		this.emailId = emailId;
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", emailId=" + emailId + "]";
	}
	
	
	
	
}
