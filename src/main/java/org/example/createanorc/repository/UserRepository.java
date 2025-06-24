package org.example.createanorc.repository;

import org.example.createanorc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getById(int id);

    User getByUserName(String userName);

    ArrayList<User> findByIsAdmin(boolean isAdmin);

    ArrayList<User> findAll();
}
