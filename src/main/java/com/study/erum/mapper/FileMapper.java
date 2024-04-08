package com.study.erum.mapper;

import java.util.List;

import com.study.erum.model.File;

public interface FileMapper {

	void insertFile(File file);

	List<File> getFilesByPlaceName(String placeName);

	List<File> getFilesByHashTag(String hashtag);

	File getFile(Long id);

	boolean deleteFileByName(String filename);

	File getFileById(Long id);

	
	void updateFile(File file);//, String description, String address, int rating, String author , String phone_number, String sns_url, String otrher_info);
}
