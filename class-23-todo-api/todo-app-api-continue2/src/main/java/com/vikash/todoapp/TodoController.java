package com.vikash.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {


    @Autowired  // checks if there is @Bean assigns object of that to todiList does depencency injecting here with the kind of object you have
    List<Todo> todoList;  // todoList is just s reference of List<Todo>

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


    // mark todo as undone
    @GetMapping("todos/undone")
    public List<Todo> getUndoneTodos() {

        List<Todo> undoneList = new ArrayList<>();  // create a new list to be able to return undone task
        for (Todo todo : todoList) {  // iteriate once over all the todos in todoList
            if (todo.isDone() == false)  // check which tasks are marked as false and store in todo
                undoneList.add(todo);         // add the undone tasks to to undonelist


        }
        return undoneList;     // reurning the list with undone tasks when you have scanned the whole list only then return thats why we have return oustide for loop

    }

    // mark  todo as done
    @GetMapping("todos/done")
    public List<Todo> getDoneTodos() {

        List<Todo> doneList = new ArrayList<>();  // create a new list to be able to return done task
        for (Todo todo : todoList) {  // iteriate once over all the todos in todoList
            if (!todo.isDone() == false)  // check which tasks are marked as true store in todo
                doneList.add(todo);         // add the done tasks  to donelist


        }
        return doneList;     // reurning the list with done tasks when you have scanned the whole list only then return thats why we have return oustide for loop

    }
        //post
        @PostMapping("todo")
        public String addToDo (@RequestBody Todo myTodo){  // return type String
            myTodo.setCreationTimeStamp(LocalDateTime.now()); // will set creation time when adding a task
            todoList.add(myTodo); // adding string to list
            return "todo added";
        }

        // add multiple todotos at once

        @PostMapping("todos")
        public String addToDos (@RequestBody List < Todo > myTodos) {  // passing in value for object
   /*    for (Todo todo : myTodos){  // iterate over every element of myTodos  store it in todoList
            todoList.add(todo);  // take every element and append it to the list
        }*/
            todoList.addAll(myTodos); // adding list to another list
            return myTodos.size() + "todos added";
        }


        // update
        @PutMapping("name/{oldTaskContent}/{newTaskContent}")
        public String updateTodo (@PathVariable String oldTaskContent, @PathVariable String newTaskContent){
            for (int i = 0; i < todoList.size(); i++) {
                if (todoList.get(i).getContent().equals(oldTaskContent)) {


                    todoList.get(i).setContent(newTaskContent);  // for a particular todo that matched with old content string we fetehced that todo, now we have that. after
                    // this we are calling a setter and passing newTask(new content)
                    return "content is updated";
                }
            }
            return null;

        }
        //marks a task as done

    @PutMapping("todo/done/{id}")
    public String markTodoDone (@PathVariable Integer id){
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId().equals(id)) {
                todoList.get(i).setDone(true); // for a particular task id that matches id passed via pathvariable set that as done

                return "content is marked done";
            }
        }
        return "todo not found";

    }

    @PutMapping("todo/undone/{id}")
    public String markTodoUnDone (@PathVariable Integer id){
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId().equals(id)) {
                todoList.get(i).setDone(false); // for a particular task id that matches id passed via pathvariable set that as undone

                return "content is marked undone";
            }
        }
        return "todo not found";

    }


//delete

        @DeleteMapping("todo/{id}")
        public String deleteTodo (@PathVariable Integer id){
            for (int i = 0; i < todoList.size(); i++) {
                Todo todo = todoList.get(i); // creating variable todo
                if (todo.getId().equals(id)) {
                    todoList.remove(i);
                    return "todo removed";
                }
            }
            return "todo not dound";
        }

// remove muliple todos

/*    @DeleteMapping("todos/{id}/{id2}")
    public String deleteTodos (@PathVariable Integer id,@PathVariable Integer id2) {
        int counter = 0;
        for (int i = 0; i < todoList.size(); i++) {
            Todo todo = todoList.get(i); // creating variable todo
            if (todo.getId().equals(id) || todo.getId().equals(id2) )  {
                todoList.remove(i);
                counter++;
            }


        }

        if (counter == 1) {
            return  "one todo removed";
        }
        else if (counter == 2) {
            return "do removed";
        }else {
            return "no todos removed";
        }
    }*/

    @DeleteMapping("todos/{ids}")
    String removeTodos(@RequestBody List<Integer> idList) {// list of id thats why List<Integer>
        int counter = 0;
        for (int i = 0; i < todoList.size() ; i++) {  // itarate over todoslist to find our list with all taks and their respective ids

            for (int j = 0; j < idList.size() ; j++) {  // iterate idlist to find our ids we pass via requestbody
                if (todoList.get(i).getId().equals(idList.get(j))){  // if a perticular tasks id matched ith an id from idList
                    todoList.remove(i);  // remove that task
                    counter++;   // counter to check how many times we enter the if condition so we can se how many taks where delteted
                    break;  // no need to continue iterating after task  is removed so we need to break from inner loop to upper loop
                }
            }
        }
     return counter + " todos where removed";
    }



}


