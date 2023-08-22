package com.vikash.SpringbootApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class APIController {

    @Autowired
    List<String> myList;

    @RequestMapping(path = "v1",method = RequestMethod.GET)   // previous version more configurable
    public String getSometing(){
        return "Vikash is learning springboot";

    }

    @RequestMapping(path = "v1/v2",method = RequestMethod.GET)   // previous version more configurable
    public String getSometingv2(){
        return "Vikash is learning springboot secodn emthod ";

    }
    @RequestMapping(path = "names",method = RequestMethod.GET)
    public List<String> getNames(){
        return myList;
    }

    @GetMapping ("top/{topK}/names")
    public List<String> getTopKNames(@PathVariable Integer topK){ // look for topK in ath variable

      //   int topK = 5;  hard code
        List topKList = new ArrayList();
        for (int i = 0; i < topK; i ++ ){
            topKList.add( myList.get(i));
        }

        return topKList;
    }


    @DeleteMapping("name/{listName}")
    public String deleteName(@PathVariable String listName){

        myList.remove(listName);  //passing list name string to delete it.

            return "name is removed : " + listName;
        }

        @PutMapping("name/{oldName}/{newName}")  // multiple parameters
        public String replaceName( @PathVariable String oldName,@PathVariable String newName){
            for (int i = 0; i <myList.size() ; i++) { // iterates trough mylsit
               if (oldName.equals(myList.get(i))){   // check if value old name exists in any of the indexes of myList
                   myList.add(i,newName); // add this index and put the new name
                   return "list is updated";  // if method
               }
            }
            return "list is not updated";


        }

    @PostMapping("name/{name}")
    public void addName(@PathVariable String name){ // passes String for name

    myList.add(name); //addin String in list

    }

    //Request params
    @GetMapping("name/age")
    public String nameAge(@RequestParam String name, @RequestParam String age){ // need to pass string and int for name and age
        return name + age;
    }

    // mixing @PathVariabel and @ReguestParam

    @RequestMapping(path="sum/{x}", method = RequestMethod.GET)
    public Integer getSum(@PathVariable int x, @RequestParam int y){ // patvariable we pass directly, reguestParan ? key= value
        return x + y ;
    }




/*    @GetMapping("Spring-pi")
public String getSometing(){
    return "Vikash is learning springboot"

}*/

}
