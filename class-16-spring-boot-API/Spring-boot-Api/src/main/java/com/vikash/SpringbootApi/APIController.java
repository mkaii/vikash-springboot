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
    @PostMapping("name/{name}")
    public void addName(@PathVariable String name){ // look for topK in ath variable

    myList.add(name);

    }



/*    @GetMapping("Spring-pi")
public String getSometing(){
    return "Vikash is learning springboot"

}*/

}
