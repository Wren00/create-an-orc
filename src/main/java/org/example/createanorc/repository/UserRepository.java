package org.example.createanorc.repository;

import org.example.createanorc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getById(Long id);

    ArrayList<User> findByUserNameContainingIgnoreCase(String userName);

    ArrayList<User> findByAdminAndUserNameContainingIgnoreCase(boolean admin, String userName);

    ArrayList<User> findByAdmin(boolean admin);

    ArrayList<User> findAll();
}
