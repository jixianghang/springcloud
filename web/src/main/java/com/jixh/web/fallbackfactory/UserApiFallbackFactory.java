package com.jixh.web.fallbackfactory;

import com.jixh.openapi.admin.api.UserApi;
import com.jixh.openapi.admin.entity.User;
import com.jixh.web.service.UserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserApiFallbackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable cause) {
        return new UserService() {
            @Override
            public User getById(Integer id) {
                String message = cause.getMessage();
                return User.builder().name("熔断了:"+message).build();
            }

            @Override
            public List<User> getUsers() {
                return null;
            }

            @Override
            public boolean addUser(User user) {
                return false;
            }

            @Override
            public boolean deleteById(Integer id) {
                return false;
            }
        };
    }
}
