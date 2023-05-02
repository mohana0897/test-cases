package com.sun.emp.model;

public class RequestDto {
	private Long id;
	private String name;
	private String email;
	private Double sal;
	public RequestDto(String name, String email, Double sal) {
		super();
		this.name = name;
		this.email = email;
		this.sal = sal;
	}
	public RequestDto(Long id, String name, String email, Double sal) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.sal = sal;
	}
	public RequestDto() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getSal() {
		return sal;
	}
	public void setSal(Double sal) {
		this.sal = sal;
	}
	@Override
	public String toString() {
		return "RequestDto [id=" + id + ", name=" + name + ", email=" + email + ", sal=" + sal + "]";
	}
	
	
	

}
