package com.xx.webframework.restapi;

import com.xx.webframework.domain.User;
import com.xx.webframework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET,value = "/user/list")
    public List<User> getUserList(){
        return userService.findAll(PageRequest.of(0,10));
    }
}
