package com.example.demo.security;

public class JwtUser {
    private String userName;
    private String id;
    private String role;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    
}
