package org.example.createanorc.models;

import lombok.Data;

@Data
public class UserPatchDTO {

    private String userName;
    private String emailAddress;
    private String userPassword;

    private Integer availableTokens; // wrapper
    private Boolean isAdmin;         // wrapper

}