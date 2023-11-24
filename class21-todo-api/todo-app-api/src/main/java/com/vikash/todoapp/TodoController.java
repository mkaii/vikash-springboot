package com.vikash.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TodoController {


    @Autowired  // checks if there is @Bean assigns object of that to todiList
    List<Todo> todoList;  // does depencency injecting here with the kind of object you have

    //get
    @GetMapping("todos")
    public List<Todo> getAllTodos() {  // specify reurn type
        return todoList;  // returning todolist to gett all todos
    }

    // get todo based on id
    @GetMapping("todos/{todoId}")
    public Todo getTodoById(@PathVariable Integer todoId) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId().equals(todoId)) { // if a task get(i) field id getId() is equal to pathvariable
                return todoList.get(i);   // return that perticular task
            }
        }
        return null;
    }


    //post
    @PostMapping("todo")
    public String addToDo(@RequestBody Todo myTodo) {  // passing in string in myTodo via reguesstbody
        myTodo.setCreationTimeStamp(LocalDateTime.now()); // will set creation time when adding a task
        todoList.add(myTodo); // adding string to list
        return "todo added";
    }

    // update
    @PutMapping("name/{oldTaskContent}/{newTaskContent}")
    public String updateTodo(@PathVariable String oldTaskContent, @PathVariable String newTaskContent) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getContent().equals(oldTaskContent)) {
                //  return "task updated" + todoList.add(i,newTask);

                todoList.get(i).setContent(newTaskContent);  // for a particular todo that matched with old content string we fetehced that todo, now we have that. after
                // this we are calling a setter and passing newTask(new content)
                return "content is updated";
            }
        }
        return null;

    }

  /*  @PutMapping("todo/{id}/{newTask}")
    public Todo updateTodo(@PathVariable Integer id,@PathVariable String newTask ) {
        for (int i = 0; i <todoList.size() ; i++) {
            if (todoList.get(i).getId().equals(id)){
                return todoList.add(newTask)
            }

        }
    }
    */


//delete

    @DeleteMapping("todo/{id}")
    public Todo deleteTodo(@PathVariable Integer id) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId().equals(id)) {
                return todoList.remove(i);
            }
        }
        return null;
    }

}

