package org.CRUD.Learning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController{
    @RequestMapping("hello")
    public static String home(){
        return "Hello World!";
    }
    @GetMapping ("form-data")
    public static String getData(@RequestParam String name,@RequestParam String email){
        return "Name: "+name+" Email: "+email;
    }

}

