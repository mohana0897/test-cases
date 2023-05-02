package com.sun.emp.model;

public class FilterRequsetDto {
	
	private String columnName;
	private String value;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public FilterRequsetDto(String columnName, String value) {
		super();
		this.columnName = columnName;
		this.value = value;
	}
	
	public FilterRequsetDto() {
		
	}
	@Override
	public String toString() {
		return "FilterRequsetDto [columnName=" + columnName + ", value=" + value + "]";
	}
	

}
