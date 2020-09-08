package com.jixh.admin.fallbackfactory;

import com.jixh.openapi.admin.api.UserApi;
import com.jixh.openapi.admin.entity.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserApiFallbackFactory implements FallbackFactory<UserApi> {
    @Override
    public UserApi create(Throwable throwable) {
        return new UserApi() {
            @Override
            public User getById(Integer id) {

                return null;
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
