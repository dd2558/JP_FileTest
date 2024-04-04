package com.study.erum.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TripInfo {
	private int id;
    private String hashtag;
    private String place_name;
    private String description;
    private String address;
    private int rating;
    private String author;
    private String phoneNumber;
    private String snsUrl;
    private String otherInfo;
}
