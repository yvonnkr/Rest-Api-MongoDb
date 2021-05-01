package com.yvolabs.rest.api.mongodb.service;

import com.yvolabs.rest.api.mongodb.model.TodoDTO;
import com.yvolabs.rest.api.mongodb.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoDTO> getAllTodos() {
        return todoRepository.findAll();
    }

    public TodoDTO createTodo(TodoDTO todo) throws Exception {
         //throw new Exception("something went wrong, please try again.");
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            TodoDTO newTodo = todoRepository.insert(todo);
            return todoRepository.save(newTodo);
    }
}
