package com.vikas.apiannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    List<String> myList;

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


}
