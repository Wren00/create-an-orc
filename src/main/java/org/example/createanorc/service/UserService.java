package org.example.createanorc.service;

import lombok.Getter;
import lombok.Setter;
import org.example.createanorc.models.UserPatchDTO;
import org.example.createanorc.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.example.createanorc.models.User;

@Service
public class UserService {

    @Getter
    @Setter
    private ArrayList<User> userModels = new ArrayList();

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET functions

    // GET ALL with parameter arguments

    public ArrayList<User> getUsersFiltered(Boolean isAdmin, String userName) {
        if (isAdmin != null && userName != null) {
            return userRepository.findByAdminAndUserNameContainingIgnoreCase(isAdmin, userName);
        } else if (isAdmin != null) {
            return userRepository.findByAdmin(isAdmin);
        } else if (userName != null) {
            return userRepository.findByUserNameContainingIgnoreCase(userName);
        } else {
            return userRepository.findAll();
        }
    }

    public User userGETBYID(Long userId) throws IndexOutOfBoundsException{
        return userRepository.getById(userId);
    }

    // POST functions

    public User userPOST(User user) {
        user.setUserPassword(encryptPassword(user.getUserPassword()));
        return userRepository.save(user);
    }

    // PATCH functions

    // PATCH needs to accept and change only specified values in stored memory.

    public User userPATCH(Long id, UserPatchDTO patch) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (patch.getUserName() != null) {
            user.setUserName(patch.getUserName());
        }
        if (patch.getEmailAddress() != null) {
            user.setEmailAddress(patch.getEmailAddress());
        }
        if (patch.getUserPassword() != null) {
            user.setUserPassword(encryptPassword(patch.getUserPassword()));
        }
        if (patch.getAvailableTokens() != null) {
            user.setAvailableTokens(patch.getAvailableTokens());
        }
        if (patch.getIsAdmin() != null) {
            user.setAdmin(patch.getIsAdmin());
        }
        return userRepository.save(user);
    }

    // DELETE functions

    public void userDELETE(Long id) throws IndexOutOfBoundsException {

        userRepository.deleteById(id);
    }

    // Password encryption when POSTing a new user or PATCHing

    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}

