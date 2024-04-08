package com.study.erum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.erum.mapper.FileMapper;
import com.study.erum.model.File;

@Service
public class FileService {

	@Autowired
	private FileMapper fileMapper;

	// 파일 정보 및 사용자 정보를 데이터베이스에 삽입하는 메소드
	public void insertFile(File file) {
		fileMapper.insertFile(file);
	}

	public List<File> getFilesByPlaceName(String placeName) {
		return fileMapper.getFilesByPlaceName(placeName);
	}

	public List<File> getFilesByHashTag(String hashtag) {
		return fileMapper.getFilesByHashTag(hashtag);
	}

	public File getFile(Long id) {
		return fileMapper.getFile(id);
	}

	public boolean deleteFileByName(String filename) {
		return fileMapper.deleteFileByName(filename);
	}

	public File getFileById(Long id) {
		return fileMapper.getFileById(id);
	}

	

	public void updateFile(File file) {
		fileMapper.updateFile(file);
	}

}