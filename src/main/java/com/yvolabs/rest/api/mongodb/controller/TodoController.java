package com.yvolabs.rest.api.mongodb.controller;

import com.yvolabs.rest.api.mongodb.exception.TodoCollectionException;
import com.yvolabs.rest.api.mongodb.model.TodoDTO;
import com.yvolabs.rest.api.mongodb.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo) {
        try {
            todoService.createTodo(todo);
            return ResponseEntity.ok(todo);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (TodoCollectionException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllTodos() {
        List<TodoDTO> todos = todoService.getAllTodos();
        return todos.size() > 0 ?
                ResponseEntity.ok(todos) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(todos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok(todoService.getSingleTodo(id));
        } catch (TodoCollectionException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodoById(@PathVariable(value = "id") String id, @RequestBody TodoDTO todo) {
        try {
            todoService.updateTodo(id, todo);
            return ResponseEntity.ok("Updated todo with id " + id + "");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (TodoCollectionException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        try {
            todoService.deleteTodoById(id);
            return ResponseEntity.ok("Successfully deleted todo with id " + id);
        } catch (TodoCollectionException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
