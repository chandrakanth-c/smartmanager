package com.webtools.utils;

public enum Status{
	
	YetToStart("YetToStart");
	
	private String value;
	
	private Status(String value) {
		this.value=value;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
