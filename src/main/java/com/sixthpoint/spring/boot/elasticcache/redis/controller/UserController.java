package com.sixthpoint.spring.boot.elasticcache.redis.controller;

import com.sixthpoint.spring.boot.elasticcache.redis.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping( value = "/get-user" )
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(
            value = "get the user")
    @GetMapping(value = "/{user}")
    @ResponseStatus(code = HttpStatus.OK)
    public String getUser(@PathVariable String user) {
        return userService.getLongRunningTaskResult(user);
    }

    @ApiOperation(
            value = "Resets the cache for a key")
    @DeleteMapping(value = "/reset/{user}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void reset(@PathVariable String user) {
        userService.resetUser(user);
    }


}
