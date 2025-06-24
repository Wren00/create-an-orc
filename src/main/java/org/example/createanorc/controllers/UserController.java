package org.example.createanorc.controllers;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import lombok.Generated;
import org.example.createanorc.models.User;
import org.example.createanorc.models.UserPatchDTO;
import org.example.createanorc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    private final UserService userService;

    //GET with uri filtering for multiple arguments

    @GetMapping({"/users"})
    public ArrayList<User> retrieveUsers(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) Boolean isAdmin
    ) {
        return userService.getUsersFiltered(isAdmin, userName);
    }

    @GetMapping({"/users/{id}"})
    @ResponseBody
    public User getUserById(@PathVariable("id") Long id) {
        return this.userService.userGETBYID(id);
    }

    @PostMapping({"/users"})
    @ResponseBody
    public User userPost(@RequestBody User model) {
        return this.userService.userPOST(model);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<User> patchUser(
            @PathVariable Long id,
            @RequestBody UserPatchDTO patch
    ) {
        try {
            User updatedUser = userService.userPATCH(id, patch);
            return ResponseEntity.ok(updatedUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping({"/users/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void userDelete(@PathVariable(name = "id") Long id) {
        this.userService.userDELETE(id);
    }

    @Generated
    public UserController(final UserService userService) {
        this.userService = userService;
    }

}