package org.example.createanorc.models;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class Character {

    private int id;
    private String name;
    private String description;
    private int characterImageId;
    private int promptsCollectionId;
    private int userId;


    public Character(int id, String name, String description, int characterImageId, int promptsCollectionId, int userId) {}

    public int setId(int id) {
        this.id = id;
        return id;
    }


}
