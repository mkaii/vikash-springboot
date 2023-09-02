package com.vikas.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
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


    //get urgent todos:
    //sort based on due date and should be marked as undone

    @GetMapping("todo/urgent")
    public List<Todo> getUrgentTodos()
    {
        List<Todo> urgentUndoneTodos  =  new ArrayList<>();

        for(Todo todo : todoList)
        {
            if(!todo.isDone())
            {
                urgentUndoneTodos.add(todo);
            }
        }

        urgentUndoneTodos.sort((t1,t2)-> t1.getDueDateTime().compareTo(t2.getDueDateTime()));

        return urgentUndoneTodos;

    }


    //get all todos which were done on time
    @GetMapping("todo/onTime")
    public List<Todo> getTodosDoneOnTime()
    {
        List<Todo> doneTodosOnTime =  new ArrayList<>();

        for(Todo todo : todoList)
        {
            if(todo.isDone() && todo.getDoneTimeStamp().isBefore(todo.getDueDateTime()))
            {
                doneTodosOnTime.add(todo);
            }
        }

        return doneTodosOnTime;

    }

    //get all todos which were not done on time
    @GetMapping("todo/not/onTime")
    public List<Todo> getTodosNotDoneOnTime()
    {
        List<Todo> doneTodosNotOnTime =  new ArrayList<>();

        for(Todo todo : todoList)
        {
            if(todo.isDone() && todo.getDoneTimeStamp().isAfter(todo.getDueDateTime()))
            {
                doneTodosNotOnTime.add(todo);
            }
        }

        return doneTodosNotOnTime;

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
       //todoList.addAll(myTodos);

        for(Todo todo : myTodos)
        {
            todo.setCreationTimeStamp(LocalDateTime.now());
            todoList.add(todo);
        }

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


    //mark a task as done

    @PutMapping("todo/done/{id}")
    String markTodoDone(@PathVariable Integer id)
    {
        for(Todo todo : todoList)
        {

            if(todo.getId().equals(id))
            {
                if(!todo.isDone())
                {
                    todo.setDone(true);
                    todo.setDoneTimeStamp(LocalDateTime.now());
                    return "todo done";
                }
                else {
                    return "todo already marked done";
                }

            }
        }

        return "Todo not found";
    }

    @PutMapping("todo/undone/{id}")
    String markTodoUnDone(@PathVariable Integer id)
    {
        for(Todo todo : todoList)
        {
            if(todo.getId().equals(id))
            {

                if(todo.isDone())
                {
                    todo.setDone(false);
                    return "todo marked unDone";
                }
                else
                {
                    return "todo already marked un-done";
                }

            }
        }

        return "Todo not found";
    }


    //increase the num of due dates :
    @PutMapping("todo/increase/dueDate/{id}/{numDays}")
    String increaseDueDate(@PathVariable Integer id,@PathVariable Integer numDays)
    {
        for(Todo todo : todoList)
        {
            if(todo.getId().equals(id))
            {
               LocalDateTime currentDueDate = todo.getDueDateTime();

               //add numDays to original date to get new date

               LocalDateTime newDueDate = todo.getDueDateTime().plusDays(numDays);
               todo.setDueDateTime(newDueDate);
               return "todo due date updated!!!";


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

    //delete multiple todos

    @DeleteMapping("todos")
    String removeTodos(@RequestBody List<Integer> idList)
    {

        int counter = 0;
        for(int i=0;i<todoList.size();i++)
        {
            Todo currentTodoFromExistingList = todoList.get(i);
            for(int j=0;j<idList.size();j++)
            {
                if(currentTodoFromExistingList.getId().equals(idList.get(j)))
                {
                    todoList.remove(currentTodoFromExistingList);
                    counter++;
                    break;
                }
            }
        }

        return counter  + " todos were removed";

       /* int counter = 0;
        for(Todo todo : todoList)
        {

            for(Integer id : idList)
            {
                if(todo.getId().equals(id))
                {
                    todoList.remove(todo);
                    counter++;
                    break;
                }
            }
        }

        return counter  + " todos were removed";*/


      /*  Iterator<Todo> iterator = todoList.iterator();
        int counter = 0;

        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (idList.contains(todo.getId())) {
                iterator.remove(); // Safe removal using the iterator
                counter++;
            }
        }

        return counter + " todos were removed";*/


    }


    @DeleteMapping("todos/undone/time/{limit}")
    public String cleanupUndoneTasks(@PathVariable Integer limit)
    {
        int counter =0;
        for (int i=0;i<todoList.size();i++ )
        {
            Todo todo = todoList.get(i);
            Duration diff = Duration.between(LocalDateTime.now(),todo.getDueDateTime());
            if(!todo.isDone() &&  Math.abs(diff.toDays()) >= limit )
            {
                todoList.remove(todo);
                counter++;
            }
        }

        return counter + " undone expired todo were removed";
    }

    // delete   done  todos that has passed the due date  based on user input time  limit
    @DeleteMapping("todos/done/timeLimit/{limit}")
    public String cleanDoneTasks(@PathVariable Integer limit){
        int counter = 0;
        for (int i = 0; i < todoList.size() ; i++) {
            Todo todo = todoList.get(i);

            if (todo.isDone()){

                Duration diff = Duration.between(LocalDateTime.now(),todo.getDoneTimeStamp());
                if (Math.abs(diff.toDays())>= limit )
                {
                    todoList.remove(todo);
                    counter++;
                }

            }
        }
        return counter + " done expired todos were removed ";
    }





}
