package org.example.createanorc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.example.createanorc.repository.UserRepository;
import org.example.createanorc.utils.UserUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import org.example.createanorc.models.User;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Getter
    @Setter
    private ArrayList<User> userModels = new ArrayList();
    private final UserRepository userRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET functions

    // GET ALL with parameter arguments

    public ArrayList<User> getUsersFiltered(Boolean isAdmin, String userName) {
        if (isAdmin != null && userName != null) {
            return userRepository.findByIsAdminAndUserNameContainingIgnoreCase(isAdmin, userName);
        } else if (isAdmin != null) {
            return userRepository.findByIsAdmin(isAdmin);
        } else if (userName != null) {
            return userRepository.findByUserNameContainingIgnoreCase(userName);
        } else {
            return userRepository.findAll();
        }
    }

    public User userGETBYID(int userId) throws IndexOutOfBoundsException{
        return userRepository.getById(userId);
    }

    // POST functions

    public User userPOST(User model) {
        int newId = userModels.size() + 1;
        model.setId(newId);
        model.setUserPassword(encryptPassword(model.getUserPassword()));
        this.userModels.add(model);
        return new User(newId, model.getUserName(), model.getEmailAddress(), model.getUserPassword(), model.getAvailableTokens(), model.isAdmin());
    }

    // PATCH functions

    // PATCH needs to accept and change only specified values in stored memory.

    @Transactional
    public User userPATCH(int id, JsonNode updates) throws JsonProcessingException {
        int databaseId = id - 1;
        User user = userModels.get(databaseId);

        UserUtils userUtils = new UserUtils();


        // Convert entity to JsonNode -> merge -> map back to entity

        JsonNode existing = mapper.valueToTree(user);
        JsonNode merged   = userUtils.merge(existing, updates);
        User patched      = mapper.treeToValue(merged, User.class);

        // Check if userPassword was part of the users update to encrypt

        if (updates.has("userPassword")) {
            String rawPassword = patched.getUserPassword();
            String encryptedPassword = encryptPassword(rawPassword);
            patched.setUserPassword(encryptedPassword);
        }

        // Updates to a new hash everytime even if value wasn't actually changed!

        userModels.set(databaseId, patched);

        return userModels.get(databaseId);
    }

    // DELETE functions

    public void userDELETE(int id) throws IndexOutOfBoundsException {
        User deletedUser = this.userGETBYID(id);

        userModels.remove(deletedUser);
    }

    // Password encryption when POSTing a new user

    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}

