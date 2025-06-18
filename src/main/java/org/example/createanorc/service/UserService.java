package org.example.createanorc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.createanorc.utils.UserUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import org.example.createanorc.models.User;
import org.springframework.transaction.annotation.Transactional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {

    private ArrayList<User> userModels = new ArrayList();
    private AtomicInteger counter = new AtomicInteger();
    private final ObjectMapper mapper = new ObjectMapper();

    // GET functions

    public ArrayList<User> userGETALL() {
        return this.userModels;
    }

    public User userGETBYID(int userId) {
       return userModels.get(userId);
    }

    // POST functions

    public User userPOST(User model) {
        int id = this.counter.incrementAndGet();
        model.setId(id);
        model.setUserPassword(encryptPassword(model.getUserPassword()));
        this.userModels.add(model);
        return new User(id, model.getUserName(), model.getEmailAddress(), model.getUserPassword(), model.getAvailableTokens(), model.isAdmin());
    }

    // PATCH functions

    @Transactional
    public User userPATCH(int id, JsonNode updates) throws JsonProcessingException {
        User user = userModels.get(id);

        UserUtils userUtils = new UserUtils();

        // Convert entity to JsonNode -> merge -> back to entity
        JsonNode existing = mapper.valueToTree(user);
        JsonNode merged   = userUtils.merge(existing, updates);
        User patched      = mapper.treeToValue(merged, User.class);

        return userModels.set(id, patched);
    }

    // DELETE functions

    public void userDELETE(int id) throws IndexOutOfBoundsException {
        User deletedUser = this.userGETBYID(id);

        userModels.remove(deletedUser);
    }

    // password encryption

    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}

