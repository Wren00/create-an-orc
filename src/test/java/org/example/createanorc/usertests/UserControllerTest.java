package org.example.createanorc.usertests;

import org.example.createanorc.controllers.UserController;
import org.example.createanorc.models.User;
import org.example.createanorc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTests {

    @MockitoBean
    UserService userService;

    @Autowired
    private UserController controller;

    // GET tests

    @Test
    void retrieveAllUsers_whenCalled_returnsUserModels() {
        // arrange
        var userModels = new ArrayList<User>();
        when(userService.userGETALL()).thenReturn(userModels);

        // act
        var results = controller.retrieveAllUsers();

        // assert
        verify(userService, times(1)).userGETALL();

        assertNotNull(results);
        assertEquals(userModels, results);
    }

    @Test
    void getUserById_whenCalledWithValidId_returnsUser() {
        var id = 1;
        var user = User.builder()
                .userName("a user")
                .emailAddress("testemail@example.com")
                .userPassword("testpassword")
                .availableTokens(1)
                .isAdmin(false)
                .build();

        when(userService.userGETBYID(id)).thenReturn(user);

        var results = controller.getUserById(id);

        verify(userService, times(1)).userGETBYID(id);

        assertNotNull(results);
        assertEquals(user, results);
    }

    @Test
    void userGetById_whenCalledWithNotValidId_throwsException() {
        var id = 100;
        when(userService.userGETBYID(id)).thenThrow(new IndexOutOfBoundsException("Bad ID"));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            userService.userGETBYID(id);
        });
    }

    // PUT tests (this needs a JSONNode object so needs more work)


    //POST tests

    @Test
    void helloPost_whenValidDataIsSubmitted_returnsUser() {

        var newUser = User.builder()
                .userName("a new user")
                .emailAddress("testemail@example.com")
                .userPassword("testpassword")
                .availableTokens(1)
                .isAdmin(false)
                .build();

        when(userService.userPOST(newUser)).thenReturn(newUser);

        var results = controller.userPost(newUser);

        verify(userService, times(1)).userPOST(newUser);

        assertNotNull(results);
        assertEquals(newUser, results);
    }

    //DELETE tests

    @Test
    void userDELETE_whenCalledWithValidId_runsOnce() {
        var id = 1;
        doNothing().when(userService).userDELETE(id);

        controller.userDelete(id);

        verify(userService, times(1)).userDELETE(id);
    }

    @Test
    void userDelete_whenCalledWithNotValidId_throwsException() {
        var id = 100;
        doThrow(new IndexOutOfBoundsException("Bad ID")).when(userService).userDELETE(id);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            userService.userDELETE(id);
        });
    }

}