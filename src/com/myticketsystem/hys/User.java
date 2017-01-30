package com.myticketsystem.hys;

public class User {
	private String username;
	private String password;
	private String type; // admin, cashier
	private boolean enabled;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public User(String username, String password, String type, boolean enabled) {
		this.username = username;
		this.password = password;
		this.type = type;
		this.enabled = enabled;
	}
}
