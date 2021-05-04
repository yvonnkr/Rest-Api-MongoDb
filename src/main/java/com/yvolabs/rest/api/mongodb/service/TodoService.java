package com.yvolabs.rest.api.mongodb.service;

import com.yvolabs.rest.api.mongodb.exception.TodoCollectionException;
import com.yvolabs.rest.api.mongodb.model.TodoDTO;
import com.yvolabs.rest.api.mongodb.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService implements ITodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoDTO> getAllTodos() {
        List<TodoDTO> todos = todoRepository.findAll();
        return todos.size() > 0 ? todos : new ArrayList<>();
    }

    @Override
    public TodoDTO getSingleTodo(String id) throws TodoCollectionException {
        Optional<TodoDTO> todoOptional = todoRepository.findById(id);

        if (todoOptional.isEmpty()) {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }

        return todoOptional.get();
    }

    @Override
    public void createTodo(TodoDTO todo) throws TodoCollectionException {
        Optional<TodoDTO> todoOptional = todoRepository.findByTodo(todo.getTodo());

        if (todoOptional.isPresent()) {
            throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
        }

        todo.setCreatedAt(new Date(System.currentTimeMillis()));
        todoRepository.save(todo);
    }


    @Override
    public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException {
        Optional<TodoDTO> todoWithId = todoRepository.findById(id);
        Optional<TodoDTO> todoWithSameName = todoRepository.findByTodo(todo.getTodo());

        if (todoWithId.isPresent()) {
            if (todoWithSameName.isPresent() && !todoWithSameName.get().getId().equals(id)) {
                throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
            }

            TodoDTO todoToUpdate = todoWithId.get();

            todoToUpdate.setTodo(todo.getTodo());
            todoToUpdate.setDescription(todo.getDescription());
            todoToUpdate.setCompleted(todo.getCompleted());
            todoToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoToUpdate);
        } else {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteTodoById(String id) throws TodoCollectionException {
        Optional<TodoDTO> todoOptional = todoRepository.findById(id);

        if (todoOptional.isEmpty()) {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }

        todoRepository.deleteById(id);
    }
}
