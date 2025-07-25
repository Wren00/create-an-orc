package org.example.createanorc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "orcs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Long orcImagesId;
    private Long promptsCollectionId;
    private Long userId;

}
