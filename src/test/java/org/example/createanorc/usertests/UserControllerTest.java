package org.example.createanorc.usertests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.createanorc.controllers.UserController;
import org.example.createanorc.models.User;
import org.example.createanorc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTests {

    @MockitoBean
    UserService userService;

    @Autowired
    private UserController controller;
    @Autowired
    private ObjectMapper objectMapper;


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

    // PUT tests

    // test for a basic userName change.

    @Test
    void updateUser_whenOneValueUpdated_returnsPatchedUser() throws JsonProcessingException {

        int userId = 1;

        String patchJson = """
            {
              "userName": "updatedName"
            }
        """;
        JsonNode updates = objectMapper.readTree(patchJson);

        User patchedUser = new User();
        patchedUser.setId(userId);
        patchedUser.setUserName("updatedName");

        when(userService.userPATCH(userId, updates))
                .thenReturn(patchedUser);

        User result = controller.updateUser(userId, updates);

        assertNotNull(result);
        assertEquals("updatedName", result.getUserName());

        verify(userService, times(1)).userPATCH(userId, updates);
    }

    // test for multiple values. userName change but also marking isAdmin as true.

    @Test
    void updateUser_whenMultipleValuesUpdated_returnsPatchedUser() throws JsonProcessingException {

        int userId = 1;

        String patchJson = """
            {
              "userName": "newAdminUser",
              "isAdmin": true
            }
        """;
        JsonNode updates = objectMapper.readTree(patchJson);

        User patchedUser = new User();
        patchedUser.setId(userId);
        patchedUser.setUserName("updatedName");
        patchedUser.setAdmin(true);


        when(userService.userPATCH(userId, updates))
                .thenReturn(patchedUser);

        User result = controller.updateUser(userId, updates);

        assertNotNull(result);
        assertEquals("updatedName", result.getUserName());
        assertTrue(result.isAdmin());

        verify(userService, times(1)).userPATCH(userId, updates);
    }

    // test for invalid data JSONProcessingException

    @Test
    void updateUser_whenUserServiceThrowsJsonProcessingException_throwsException() throws JsonProcessingException {

        int userId = 1;
        String patchJson = """
            {
              "userName": "invalid!"
            }
        """;

        JsonNode patchNode = objectMapper.readTree(patchJson);

        // throw JsonProcessingException from service
        when(userService.userPATCH(userId, patchNode))
                .thenThrow(new JsonProcessingException("Mocked JSON error") {});

        assertThrows(JsonProcessingException.class, () -> {
            controller.updateUser(userId, patchNode);
        });

        verify(userService).userPATCH(userId, patchNode);
    }

    @Test
    void updateUser_whenCalledWithNotValidId_throwsException() {
        var id = 100;
        doThrow(new IndexOutOfBoundsException("Bad ID")).when(userService).userDELETE(id);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            userService.userDELETE(id);
        });
    }



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