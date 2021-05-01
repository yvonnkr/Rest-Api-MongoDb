package com.yvolabs.rest.api.mongodb.controller;

import com.yvolabs.rest.api.mongodb.model.TodoDTO;
import com.yvolabs.rest.api.mongodb.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@Slf4j
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllTodos() {
        log.info("getAllTodos method of TodoController");
        List<TodoDTO> allTodos = todoService.getAllTodos();
        return allTodos.size() > 0 ?
                ResponseEntity.ok(allTodos) :
                new ResponseEntity<>("No todos available", HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo) {
        try {
            TodoDTO addedTodo = todoService.createTodo(todo);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedTodo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorMessage(e));
        }
    }

    private HashMap<String, String> getErrorMessage(Exception e) {
        HashMap<String, String> errorMessage = new HashMap<>();
        errorMessage.put("message", e.getMessage());
        return errorMessage;
    }

}
