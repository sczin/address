package com.bayviewglen.book;

public class Contact {
	private String lname;
	private String fname;
	private String phone;
	
	public Contact(){
		setLname("");
		setFname("");
		setPhone("");
	}
	
	public Contact(String lname, String fname, String phone){
		setLname(lname);
		setFname(fname);
		setPhone(phone.replaceAll("\\s+",""));
	}
	
	public Contact(String contactString){
		String[] info = contactString.split(",");
		setLname(info[0].trim());
		setFname(info[1].trim());
		setPhone(info[2].replaceAll("\\s+",""));
	}
	
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String toString(){
		return lname + ", " +  fname + ", " + phone;
	}
}
