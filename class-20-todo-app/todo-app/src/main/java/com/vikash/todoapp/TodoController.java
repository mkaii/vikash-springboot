package com.vikash.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {


    @Autowired  // checks if there is @Bean assigns object of that to todiList
    List<Todo> todoList;  // does depencency injecting here with the kind of object you have

    // get all todos
@GetMapping("allTodo")
    public List<Todo> getTodoList() {
        return todoList;
    }
    // add a todo
    @PostMapping ("todos")
    public String addTodo(@RequestBody Todo todo) {

        todoList.add(todo);
        return "Todo added";
    }


// delete a todo
@DeleteMapping("todo/{id}")
    public String delete(@PathVariable Integer id) {

    for (int i = 0; i < getTodoList().size(); i++) {
        if (i == id) {
            todoList.remove(id - 1);
            return "removed todo : " + id;

        }
    }
return "todo is not removed as id does not exist";
}


    // get a todo based on id
    @GetMapping("todos/{todoId}")
    public Todo getTodoById(@PathVariable Integer todoId)
    {
        return todoList.get(todoId -1);
    }

    @PutMapping("todo/{oldConent}/{newContent}")
    public String replaceContent( @PathVariable String oldConent , @PathVariable String newContent){
        for (int i = 0; i <getTodoList().size() ; i++) {
            if (oldConent.equals(todoList.get(i))){
                todoList.a
                return "content is updated";
            }
        }
        return "content is not updated";



    }
