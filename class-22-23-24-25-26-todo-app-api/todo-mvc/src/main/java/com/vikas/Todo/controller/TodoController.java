package com.vikas.Todo.controller;

import com.vikas.Todo.service.TodoService;
import com.vikas.Todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    //@Autowired
    TodoService todoService;

    //get

    @GetMapping("todos")
    public List<Todo> getAllTodos()
    {
       return todoService.getAllTodos();
    }


    @GetMapping("todos/{todoId}")
    public Todo getTodo(@PathVariable Integer todoId)
    {


        return todoService.getTodoById(todoId);
    }


    //get all undone tasks :

    @GetMapping("todos/undone")
    public List<Todo> getAllUndoneTodos()
    {
        return todoService.getAllUndoneTodos();
    }


    //get all done tasks :
    @GetMapping("todos/done")
    public List<Todo> getAllDoneTodos()
    {
        return todoService.getAllDoneTodos();
    }


    //get urgent todos:
    //sort based on due date and should be marked as undone

    @GetMapping("todos/urgent")
    public List<Todo> getUrgentTodos()
    {


        return todoService.getUrgentTodos();

    }


    //get all todos which were done on time
    @GetMapping("todos/onTime")
    public List<Todo> getTodosDoneOnTime()
    {
       return todoService.getTodosDoneOnTime();

    }

    //get all todos which were not done on time
    @GetMapping("todos/not/onTime")
    public List<Todo> getTodosNotDoneOnTime()
    {
       return todoService.getTodosNotDoneOnTime();

    }



    //post

    //add 1 task
    @PostMapping("todo")
    public String addTodo(@RequestBody Todo myTodo)
    {


        return todoService.addTodo(myTodo);

    }

    //add multiple tasks at once
    @PostMapping("todos")
    public String addTodos(@RequestBody List<Todo> myTodos)
    {

        return todoService.addTodos(myTodos);

    }






    //update

    @PutMapping("todo/name/{oldContent}/{newContent}")
    String updateTodo(@PathVariable String oldContent,@PathVariable String newContent)
    {


        return todoService.updateTodo(oldContent,newContent);
    }


    //mark a task as done

    @PutMapping("todo/done/{id}")
    String markTodoDone(@PathVariable Integer id)
    {


        return todoService.markTodoDone(id);
    }

    @PutMapping("todo/undone/{id}")
    String markTodoUnDone(@PathVariable Integer id)
    {



        return todoService.markTodoUnDone(id);
    }


    //increase the num of due dates :
    @PutMapping("todo/increase/dueDate/{id}/{numDays}")
    String increaseDueDate(@PathVariable Integer id,@PathVariable Integer numDays)
    {
        return todoService.increaseDueDate(id,numDays);
    }



    //delete

    @DeleteMapping("todo/{todoId}")
    public String removeTodo(@PathVariable Integer todoId)
    {



        return todoService.removeTodo(todoId);
    }

    //delete multiple todos

    @DeleteMapping("todos")
    String removeTodos(@RequestBody List<Integer> idList)
    {
        return todoService.removeTodos(idList);

    }


    @DeleteMapping("todos/undone/time/{limit}")
    public String cleanupUndoneTasks(@PathVariable Integer limit)
    {
        return todoService.cleanupUndoneTasks(limit);
    }

    // delete   done  todos that has passed the due date  based on user input time  limit
    @DeleteMapping("todos/done/timeLimit/{limit}")
    public String cleanDoneTasks(@PathVariable Integer limit){

        return todoService.cleanDoneTasks(limit);
    }





}
