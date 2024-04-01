package com.study.erum.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String username;
    private String content;
    private LocalDateTime createdAt;

    // Getters and setters
}
