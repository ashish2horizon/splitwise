package com.example.splitwise.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.splitwise.model.Activity;

public interface ActivityRepository extends MongoRepository<Activity, ObjectId> {

}
