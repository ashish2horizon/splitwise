package com.example.splitwise.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.splitwise.model.Expense;

public interface ExpenseRepository extends MongoRepository<Expense,ObjectId> {
	
	Expense findBy_id(ObjectId id);

}
