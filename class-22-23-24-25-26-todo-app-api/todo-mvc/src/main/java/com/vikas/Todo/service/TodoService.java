package com.vikas.Todo.service;

import com.vikas.Todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    List<Todo> todoList;


    public List<Todo> getAllTodos() {
        return todoList;
    }

    public Todo getTodoById(Integer todoId) {

        for (int i=0;i<todoList.size();i++ )
        {
            if(todoList.get(i).getId().equals(todoId))
            {
                return todoList.get(i);
            }
        }

        return null;
    }


    public List<Todo> getAllUndoneTodos() {
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


    public List<Todo> getAllDoneTodos() {
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

    public List<Todo> getUrgentTodos() {

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

    public List<Todo> getTodosDoneOnTime() {

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

    public List<Todo> getTodosNotDoneOnTime() {

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

    public String addTodo(Todo myTodo) {

        myTodo.setCreationTimeStamp(LocalDateTime.now());
        todoList.add(myTodo);
        return "Todo added!";
    }

    public String addTodos(List<Todo> myTodos) {

        //todoList.addAll(myTodos);

        for(Todo todo : myTodos)
        {
            todo.setCreationTimeStamp(LocalDateTime.now());
            todoList.add(todo);
        }

        return myTodos.size() + " todos were added";

    }

    public String markTodoDone(Integer id) {

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

    public String markTodoUnDone(Integer id) {

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


    public String increaseDueDate(Integer id, Integer numDays) {

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


    public String updateTodo(String oldContent, String newContent) {

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


    public String removeTodo(Integer todoId) {

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

    public String removeTodos(List<Integer> idList) {

        int counter= 0;

        for(Integer id : idList)
        {
            for(Todo todo : todoList)
            {
                if(id.equals(todo.getId()))
                {
                    todoList.remove(todo);
                    counter++;
                    break;
                }
            }
        }

        return counter  + " todos were removed";

       /* int counter = 0;
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

        return counter  + " todos were removed";*/



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

    public String cleanupUndoneTasks(Integer limit) {

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

    public String cleanDoneTasks(Integer limit) {

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
