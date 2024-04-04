package com.study.erum.mapper;

import java.util.List;

import com.study.erum.model.File;

public interface FileMapper {
	
    void insertFile(File file);
    
    List<File> getFilesByPlaceName(String placeName);
    
    List<File> getFilesByHashTag(String hashtag);
    
    File getFile(Long id);
}
