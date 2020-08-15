package com.webtools.pojo;

import java.util.ArrayList;
import java.util.List;



public class User {
	
	private int userid;
	private String uname;
	private String upassword;
	
	public User() {
			
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

}
