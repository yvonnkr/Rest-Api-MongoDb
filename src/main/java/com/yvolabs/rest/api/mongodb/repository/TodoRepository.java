package com.yvolabs.rest.api.mongodb.repository;

import com.yvolabs.rest.api.mongodb.model.TodoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<TodoDTO, String> {
}
