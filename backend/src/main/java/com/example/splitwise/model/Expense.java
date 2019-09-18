package com.example.splitwise.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "expenses")
public class Expense {
	
	@Id
	public ObjectId _id;
	
	private String description;
	private int price;
	private String paidBy;
	private String[] split; 
	
	
	
	public String[] getSplit() {
		return split;
	}

	public void setSplit(String[] split) {
		this.split = split;
	}

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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPaidBy() {
		return paidBy;
	}
	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}
	
	
	

}
