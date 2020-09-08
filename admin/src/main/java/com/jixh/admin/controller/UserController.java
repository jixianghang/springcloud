
package com.jixh.admin.controller;


import com.jixh.openapi.admin.api.UserApi;
import com.jixh.openapi.admin.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
public class UserController implements UserApi {
    private static ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<Integer,User>();

    static {

        for (int i =0 ;i<100;i++){

            users.put(i,User.builder().id(i).name("张"+i).age(18).build());
        }
    }

    @Override
    public User getById(@PathVariable("id") Integer id) {


        return this.users.get(id);
    }

    @Override
    public List<User> getUsers() {
        return users.values().stream().map(x -> {if(1==1) return x;
            return null;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean addUser(@RequestBody User user) {
        Optional<Integer> max = users.keySet().stream().max(Comparator.comparingInt(x -> x.intValue()));
        users.put(max.get()+1,user);
        return true;
    }

    @Override
    public boolean deleteById(@PathVariable("id") Integer id) {
        if(users.containsKey(id)){
            users.remove(id);
            return true;
        }
        return false;
    }
}


