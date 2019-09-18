package com.example.splitwise.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class User {
	
	@Id
	public ObjectId _id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int owe;
	private int owed;
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String get_id() { 
		return _id.toHexString();
		}
	
	public void set_id(ObjectId _id) {
		this._id = _id; 
		}	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getOwe() {
		return owe;
	}
	public void setOwe(int owe) {
		this.owe = owe;
	}
	public int getOwed() {
		return owed;
	}
	public void setOwed(int owed) {
		this.owed = owed;
	}

	@Override
	public String toString() {
		return "User [_id=" + _id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", owe=" + owe + ", owed=" + owed + "]";
	}
	
	
	
	

}
