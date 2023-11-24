package com.vikas.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TodoController {

    @Autowired
    List<Todo> todoList;

    //get

    @GetMapping("todos")
    public List<Todo> getAllTodos()
    {
        return todoList;
    }


    @GetMapping("todos/{todoId}")
    public Todo getAllTodos(@PathVariable Integer todoId)
    {
        for (int i=0;i<todoList.size();i++ )
        {
            if(todoList.get(i).getId().equals(todoId))
            {
                return todoList.get(i);
            }
        }

        return null;
    }




    //post

    @PostMapping("todo")
    public String addTodo(@RequestBody Todo myTodo)
    {
        myTodo.setCreationTimeStamp(LocalDateTime.now());
        todoList.add(myTodo);
        return "Todo added!";
    }


    //update



    //delete

}
