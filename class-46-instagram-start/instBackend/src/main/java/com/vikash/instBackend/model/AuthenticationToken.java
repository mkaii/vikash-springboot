package com.vikash.instBackend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    private String tokenValue;

    private LocalDateTime tokenCreationDateTime;

    @OneToOne
    @JoinColumn(name = "fk_user_Id")
    User user;
}
