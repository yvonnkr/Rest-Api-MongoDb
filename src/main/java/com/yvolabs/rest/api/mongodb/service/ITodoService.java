package com.yvolabs.rest.api.mongodb.service;

import com.yvolabs.rest.api.mongodb.exception.TodoCollectionException;
import com.yvolabs.rest.api.mongodb.model.TodoDTO;

import java.util.List;

public interface ITodoService
    public List<TodoDTO> getAllTodos();

    public TodoDTO getSingleTodo(String id) throws TodoCollectionException;

    public void createTodo(TodoDTO todo) throws TodoCollectionException;

    public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException;

    public void deleteTodoById(String id) throws TodoCollectionException;
}
