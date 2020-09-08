
package com.jixh.web.service;


import com.jixh.openapi.admin.api.UserApi;
import com.jixh.openapi.admin.entity.User;
import com.jixh.web.config.FeignAuthConfigurationweb;
import com.jixh.web.fallbackfactory.UserApiFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "admin" ,configuration = FeignAuthConfigurationweb.class,fallbackFactory = UserApiFallbackFactory.class)
//s@RequestMapping(value = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
public interface UserService extends UserApi {

}

