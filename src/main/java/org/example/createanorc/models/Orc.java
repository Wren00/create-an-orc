package org.example.createanorc.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orc {

    private int id;
    private String name;
    private String description;
    private int orcImageId;
    private int promptsCollectionId;
    private int userId;

}
