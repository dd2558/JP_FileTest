package com.study.erum.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class File {
	private Long id;
    private String username;
    private String placeName;
    private String filename;
    private String filepath;
    private LocalDateTime createdAt;

}
