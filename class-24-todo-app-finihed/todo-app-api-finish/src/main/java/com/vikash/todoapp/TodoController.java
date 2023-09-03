package com.vikash.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
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

    // get urgent tasks, sort based on due date and should be marked as undone
    @GetMapping("todo/urgent")
    public List<Todo> getUrgentTasks(){    // returning a list of todos

        List<Todo> urgentUndoneTodos = new ArrayList<>();  // list of undone todos
        for (Todo todo : todoList) {


         if (!todo.isDone()){
             urgentUndoneTodos.add(todo);
         }
        }
           urgentUndoneTodos.sort((t1,t2)-> t1.getDueDate().compareTo(t2.getDueDate()));  // tell it to sort then tell it how it should be sorted(if i get 2 todos this is how i want them to compare) if the date caller is less then what i have passed inside comapre to we will get negativa response and it will sort based on that(acending)
          return urgentUndoneTodos;
    }


    // get all undone todos
    @GetMapping("todos/undone")
    public List<Todo> getUndoneTodos() {

        List<Todo> undoneList = new ArrayList<>();  // create a new list to be able to return undone task
        for (Todo todo : todoList) {  // iteriate once over all the todos in todoList
            if (todo.isDone() == false)  // check which tasks are marked as false and store in todo
                undoneList.add(todo);         // add the undone tasks to to undonelist


        }
        return undoneList;     // reurning the list with undone tasks when you have scanned the whole list only then return thats why we have return oustide for loop

    }

    // get all done todos
    @GetMapping("todos/done")
    public List<Todo> getDoneTodos() {

        List<Todo> doneList = new ArrayList<>();  // create a new list to be able to return done task
        for (Todo todo : todoList) {  // iteriate once over all the todos in todoList
            if (!todo.isDone() == false)  // check which tasks are marked as true store in todo
                doneList.add(todo);         // add the done tasks  to donelist


        }
        return doneList;     // reurning the list with done tasks when you have scanned the whole list only then return thats why we have return oustide for loop

    }

    // get all todos that where done on time
    @GetMapping("todo/onTime")
    public List<Todo> doneOneTime(){

        List<Todo> doneOntime = new ArrayList<>(); // creating new list tostore todos done in time
        for (Todo todo:todoList){ // traversing trough the list
            if (todo.isDone() && todo.getDoneTimeStamp().isBefore(todo.getDueDate())){  // if task is doneTimetamp is  before due date
                doneOntime.add(todo);  // add those task to done on tume
            }

        }
        return doneOntime;  // return the done on time list
        }
    // get all todos that where not done on time
    @GetMapping("todo/notOnTime")
    public List<Todo> notDoneOneTime(){

        List<Todo> notDoneOnTime = new ArrayList<>(); // creating new list tostore todos not done in time
        for (Todo todo:todoList){ // traversing trough the list
            if (todo.isDone() && todo.getDoneTimeStamp().isAfter(todo.getDueDate())){  // id task is doneTimeStamp is after  due date
                notDoneOnTime.add(todo);  // add those task to done on tume
            }

        }
        return notDoneOnTime;  // return the done on time list
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
            for (Todo todo : myTodos) {  // iterate over every element of myTodos  store it in todoList

                todo.setCreationTimeStamp(LocalDateTime.now());
                todoList.add(todo);  // take every element and append it to the list
               // todoList.addAll(myTodos); // adding list to another list

            }
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
                todoList.get(i).setDoneTimeStamp(LocalDateTime.now()); // will register time when marking as done
                return "todo done";
            }
        }
        return "todo not found";

    }
    //marks a task as undone
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

    // increase num of due date by days
    @PutMapping("todo/increase/{id}/{numDays}")
    String inceraseDueDate(@PathVariable Integer id, @PathVariable Integer numDays) {
        for (Todo todo : todoList) {

            if (todo.getId().equals(id)) {   // if id matches
                LocalDateTime currentDueDate = todo.getDueDate(); // getting current due date

                // add number of days to orgiginal due date
                LocalDateTime newdate = todo.getDueDate().plusDays(numDays); // add num days to the original due date via plusDays method
                todo.setDueDate(newdate);  // setting that new due date
                return "due date is updated ";
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

// delete multiple todos
    @DeleteMapping("todos/ids")
    String removeTodos(@RequestBody List<Integer> idList) {// list of id thats why List<Integer>
        int counter = 0;
        for (int i = 0; i < todoList.size() ; i++) {  // itarate over todoslist to find our list with all taks and their respective ids
            Todo currentTodoFromExistingList= todoList.get(i);
            for (int j = 0; j < idList.size() ; j++) {  // iterate idlist to find our ids we pass via requestbody
                if (currentTodoFromExistingList.getId().equals(idList.get(j))){  // if a perticular tasks id matched ith an id from idList
                    todoList.remove(currentTodoFromExistingList);  // remove that task
                    counter++;   // counter to check how many times we enter the if condition so we can se how many taks where delteted
                    break;  // no need to continue iterating after task  is removed so we need to break from inner loop to upper loop
                }
            }
        }
     return counter + " todos where removed";
    }

// delete   undone  todos that has passed the due date  based on user input time  limit
    @DeleteMapping("todos/{limit}")
    public String cleanUndoneTasks(@PathVariable Integer limit){
        int counter = 0;
        for (int i = 0; i < todoList.size() ; i++) {
            Todo todo = todoList.get(i);
            Duration diff = Duration.between(LocalDateTime.now(),todo.getDueDate());
           if (!todo.isDone() && -Math.abs(diff.toDays())>= limit){  // if task is undone and
               todoList.remove(todo);
               counter++;
           }
        }
        return counter + " undone expired todos were removed ";
    }

    // delete   done  todos that has passed the due date  based on user input time  limit
    @DeleteMapping("todos1/{limit2}")
    public String cleanDoneTasks(@PathVariable Integer limit2){
        int counter = 0;
        for (int i = 0; i < todoList.size() ; i++) {
            Todo todo = todoList.get(i);
            Duration diff = Duration.between(LocalDateTime.now(),todo.getDueDate());
            if (todo.isDone() && -Math.abs(diff.toDays())>= limit2){
                todoList.remove(todo);
                counter++;
            }
        }
        return counter + " done expired todos were removed ";
    }





}


