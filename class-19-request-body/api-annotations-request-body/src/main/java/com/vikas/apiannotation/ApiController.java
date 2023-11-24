package com.vikas.apiannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ApiController {

    @Autowired
    List<String> myList;


    //path variables
    @RequestMapping(path = "v1", method = RequestMethod.GET)
    public String getSomething()
    {
        return "vikas is learning springboot";
    }

    @RequestMapping(path = "v1/v2", method = RequestMethod.GET)
    public String getSomethingV1V2()
    {
        return "vikas is learning springboot -v1-v2";
    }

    @RequestMapping(path = "names",method = RequestMethod.GET)
    public List<String> getNames()
    {
        return myList;
    }

    @GetMapping("top/{topK}/names")
    public List<String> getTopKNames(@PathVariable Integer topK)
    {

        List<String> topKlist = new ArrayList<>();
        for (int i=0;i<topK;i++)
        {
            topKlist.add(myList.get(i));
        }

        return topKlist;
    }

    @PostMapping("name/{name}")
    public void addName(@PathVariable String  name)
    {

        myList.add(name);
    }

    @DeleteMapping("name/{listName}")
    public String removeName(@PathVariable String listName)
    {
        myList.remove(listName);
        return "removed name: "   +  listName;
    }

    @PutMapping("name/{oldName}/{newName}")
    public String updateName(@PathVariable String oldName, @PathVariable String newName)
    {
        for(int i=0;i<myList.size();i++)
        {
            if(oldName.equals(myList.get(i)))
            {
                myList.add(i,newName);
                return "List is updated";
            }
        }
        return "List is not updated";

    }

    //request params - query params

    @GetMapping("name/age")
    public String getNameAndAge(@RequestParam String name, @RequestParam Integer age)
    {
        return name + age;
    }

    //mix the above 2 approaches :

    @RequestMapping(path = "sum/{x}",method = RequestMethod.GET)
    public String getSum(@PathVariable int x, @RequestParam int y)
    {
        return String.valueOf(x+y);
    }


    @GetMapping("number/below")
    List<Integer> getNumbers(@RequestBody Integer num)
    {
        List <Integer>list = new ArrayList<>();
        for(int i =0;i < num;i++)
        {
            list.add(i+1);
        }
        return list;
    }

    @GetMapping("student")
    public Student getStudent(@RequestBody Student s1)
    {
        System.out.println(s1.toString());
        return s1;
    }



}
