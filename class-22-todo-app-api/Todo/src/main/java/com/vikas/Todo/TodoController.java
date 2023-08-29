package com.vikas.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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


    //get all undone tasks :

    @GetMapping("todos/undone")
    public List<Todo> getAllUndoneTodos()
    {
        List<Todo> undoneTasks = new ArrayList<>();

        for(Todo todo : todoList)
        {
            if(!todo.isDone())
            {
                undoneTasks.add(todo);
            }
        }

        return  undoneTasks;
    }


    //get all done tasks :
    @GetMapping("todos/done")
    public List<Todo> getAllDoneTodos()
    {
        List<Todo> doneTasks = new ArrayList<>();

        for(Todo todo : todoList)
        {
            if(todo.isDone())
            {
                doneTasks.add(todo);
            }
        }

        return  doneTasks;
    }




    //post

    //add 1 task
    @PostMapping("todo")
    public String addTodo(@RequestBody Todo myTodo)
    {
        myTodo.setCreationTimeStamp(LocalDateTime.now());
        todoList.add(myTodo);
        return "Todo added!";
    }

    //add multiple tasks at once
    @PostMapping("todos")
    public String addTodos(@RequestBody List<Todo> myTodos)
    {
       todoList.addAll(myTodos);

       /* for(Todo todo : myTodos)
        {
            todoList.add(todo);
        }*/

        return myTodos.size() + " todos were added";
    }






    //update

    @PutMapping("todo/name/{oldContent}/{newContent}")
    String updateTodo(@PathVariable String oldContent,@PathVariable String newContent)
    {
        for(Todo todo : todoList)
        {
            if(todo.getContent().equals(oldContent))
            {
                todo.setContent(newContent);
                return "todo updated";
            }
        }

        return "Todo not found";
    }



    //delete

    @DeleteMapping("todo/{todoId}")
    public String removeTodo(@PathVariable Integer todoId)
    {
        for (int i=0;i<todoList.size();i++ )
        {
            Todo todo = todoList.get(i);
            if(todo.getId().equals(todoId))
            {
               todoList.remove(todo);
               return "todo removed ";
            }
        }

        return "todo not found";
    }


}
