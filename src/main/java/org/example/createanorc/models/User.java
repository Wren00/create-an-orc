package org.example.createanorc.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class User {

    private int id;
    private String userName;
    private String emailAddress;
    private String userPassword;
    private int availableTokens;
    private boolean isAdmin;

}

