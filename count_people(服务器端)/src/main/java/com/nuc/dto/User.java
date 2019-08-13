package com.nuc.dto;

public class User {
	private Integer id;
    private String username;
    private String password;
    private String role;
    private String phonenum;
    
    
    
    public User()
    {}
    
	public User(Integer id, String username, String password, String role, String phonenum) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.phonenum = phonenum;
	}

	public User( String username, String password)
	{
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", phonenum="
				+ phonenum + "]";
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
    
    
    

}
