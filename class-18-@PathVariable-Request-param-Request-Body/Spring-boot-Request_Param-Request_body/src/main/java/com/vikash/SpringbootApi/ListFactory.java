package com.vikash.SpringbootApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class ListFactory {



@Bean
public ArrayList<String> getList(){
    ArrayList<String> names= new ArrayList<>();
    names.add("vikash");
    names.add("mainaik");
    names.add("anders");
    names.add("benny");
    names.add("jetli");
    names.add("jackie");
    names.add("amanda");
    names.add("jerry");
    return names;

}

}


