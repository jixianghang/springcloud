package com.jixh.openapi.admin.api;


import com.jixh.openapi.admin.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
public interface UserApi {

    @GetMapping(value = "/getById/{id}")
    public User getById(@PathVariable Integer id);

    @GetMapping("/getUsers")
    public List<User> getUsers();

    @PostMapping("/addUser")
    public boolean addUser(@RequestBody User user);

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Integer id);
}
