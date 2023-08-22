package com.vikas.apiannotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ListFactory {

    @Bean
    public List<String> getList()
    {
        //return List.of("Mainak","vikas");
        List<String> names = new ArrayList<>();
        names.add("Mainak");
        names.add("Vikas");
        names.add("Ravi");
        names.add("Bobby");
        names.add("Luna");
        names.add("Mark");
        names.add("Meg");
        names.add("Nikhil");

        return names;
    }
}
