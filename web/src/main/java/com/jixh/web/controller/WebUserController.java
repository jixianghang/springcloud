
package com.jixh.web.controller;


import com.jixh.openapi.admin.entity.User;
import com.jixh.web.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/webUser",produces = MediaType.APPLICATION_JSON_VALUE)
public class WebUserController {

   @Autowired
   private UserService userService;

    @GetMapping("/getById/{id}")
    @HystrixCommand(fallbackMethod = "getHystixById")
    public User getById(@PathVariable("id") Integer id) {

        return userService.getById(id);
    }

    public User getHystixById(Integer id) {

        return null;
    }
    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/addUser")
    public boolean addUser(@RequestBody User user) {

        return userService.addUser(user);
    }

    @PostMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable("id") Integer id) {

        return userService.deleteById(id);
    }
}

