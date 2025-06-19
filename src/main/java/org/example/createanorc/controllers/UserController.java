package org.example.createanorc.controllers;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Generated;
import org.example.createanorc.models.User;
import org.example.createanorc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    private final UserService userService;

    @GetMapping({"/users"})
    @ResponseBody
    public ArrayList<User> retrieveAllUsers() {
        return this.userService.userGETALL();
    }

    @GetMapping({"/users/{id}"})
    @ResponseBody
    public User getUserById(@PathVariable("id") int id) {
        return this.userService.userGETBYID(id);
    }

    @PatchMapping({"/users/{id}"})
    @ResponseBody
    public User updateUser(@PathVariable("id") int id, @RequestBody JsonNode updates) throws JsonProcessingException {
        return this.userService.userPATCH(id, updates);
    }

    @PostMapping({"/users"})
    @ResponseBody
    public User userPost(@RequestBody User model) {
        return this.userService.userPOST(model);
    }

    @DeleteMapping({"/users/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void userDelete(@PathVariable(name = "id") int id) {
        this.userService.userDELETE(id);
    }

    @Generated
    public UserController(final UserService userService) {
        this.userService = userService;
    }

}