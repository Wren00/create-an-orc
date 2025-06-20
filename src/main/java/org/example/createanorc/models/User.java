package org.example.createanorc.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String userName;
    private String emailAddress;
    private String userPassword;
    private int availableTokens;
    private boolean isAdmin;

}

