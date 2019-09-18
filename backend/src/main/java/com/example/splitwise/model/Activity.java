package com.example.splitwise.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "activity")
public class Activity {
	
	@Id
	public ObjectId _id;
	
	private String description;
	private String firstName;
	private int owes;
	private String owesTo;
	private int owed;
	private String owedBy;
	
	
	public String get_id() { 
		return _id.toHexString();
		}
	
	public void set_id(ObjectId _id) {
		this._id = _id; 
		}	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getOwes() {
		return owes;
	}
	public void setOwes(int owes) {
		this.owes = owes;
	}
	public String getOwesTo() {
		return owesTo;
	}
	public void setOwesTo(String owesTo) {
		this.owesTo = owesTo;
	}
	public int getOwed() {
		return owed;
	}
	public void setOwed(int owed) {
		this.owed = owed;
	}
	public String getOwedBy() {
		return owedBy;
	}
	public void setOwedBy(String owedBy) {
		this.owedBy = owedBy;
	}
	
	
	

}
