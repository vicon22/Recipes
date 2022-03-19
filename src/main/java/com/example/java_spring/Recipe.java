package com.example.java_spring;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "recipes")
class Recipe{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "category")
    @NotBlank
    private String category;

    @Column(name = "date")
    //@NotBlank
    private LocalDateTime date;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "ingredients")
    @NotNull
    @Size(min = 1)
    @ElementCollection
    private List<String> ingredients = new ArrayList<>();

    @Column(name = "directions")
    @NotNull
    @Size(min = 1)
    @ElementCollection
    private List<String> directions = new ArrayList<>();
}
