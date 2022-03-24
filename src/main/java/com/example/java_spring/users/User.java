package com.example.java_spring.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name = "email")
    @NotBlank
    @Pattern(regexp = ".+@.+\\..+")
    private String email;

    @Column(name = "password")
    @NotBlank
    @Size(min = 8)
    private String password;

    @Column(name = "role")
    private String role = "USER";
}
