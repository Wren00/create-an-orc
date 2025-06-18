package org.example.createanorc.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class Character {

    private int id;
    private String name;
    private String description;
    private int characterImageId;
    private int promptsCollectionId;
    private int userId;

}
