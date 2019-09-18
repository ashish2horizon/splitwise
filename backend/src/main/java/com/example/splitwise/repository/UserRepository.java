package com.example.splitwise.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.splitwise.model.User;
import com.example.splitwise.model.Expense;

public interface UserRepository extends  MongoRepository<User,ObjectId> {
	
	Expense findBy_id(ObjectId id);
	
	User findByEmail(String email);
	User findByFirstName(String firstName);

}
