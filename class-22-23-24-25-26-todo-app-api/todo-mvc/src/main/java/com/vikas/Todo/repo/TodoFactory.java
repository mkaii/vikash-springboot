package com.vikas.Todo.repo;

import com.vikas.Todo.model.Todo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TodoFactory {

    @Bean
    List<Todo> initTodoList()
    {
        return new ArrayList<>();
    }
}
