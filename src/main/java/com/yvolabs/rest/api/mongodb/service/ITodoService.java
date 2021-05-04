package com.yvolabs.rest.api.mongodb.service;

import com.yvolabs.rest.api.mongodb.exception.TodoCollectionException;
import com.yvolabs.rest.api.mongodb.model.TodoDTO;

import java.util.List;

public interface ITodoService{
    List<TodoDTO> getAllTodos();

    TodoDTO getSingleTodo(String id) throws TodoCollectionException;

    void createTodo(TodoDTO todo) throws TodoCollectionException;

    void updateTodo(String id, TodoDTO todo) throws TodoCollectionException;

    void deleteTodoById(String id) throws TodoCollectionException;
}
