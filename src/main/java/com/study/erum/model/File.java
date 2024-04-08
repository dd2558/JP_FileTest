package com.study.erum.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class File {
	private Long id;
	private String username;
	private String placeName;
	private String filename;
	private String filepath;
	private LocalDateTime createdAt;
	private String hashtag;
	private String description;
	private String address;
	private int rating;
	private String author;
	private String phone_number;
	private String sns_url;
	private String other_info;
}
