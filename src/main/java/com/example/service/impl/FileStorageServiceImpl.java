package com.example.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Exception.FileStorageException;
import com.example.config.FileStorageProperties;
import com.example.service.FileStorageService;
@Service
public class FileStorageServiceImpl implements FileStorageService{

	@Override
	public String storeFile(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource loadFileAsResource(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * private final Path fileStorageLocation ;
	 * 
	 * @Autowired public FileStorageServiceImpl(FileStorageProperties
	 * fileStorageProperties) {
	 * this.fileStorageLocation=Paths.get(fileStorageProperties.getUploadDir()).
	 * toAbsolutePath().normalize(); try {
	 * Files.createDirectories(this.fileStorageLocation); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 * 
	 * @Override public String storeFile(MultipartFile file) throws IOException {
	 * if(!(file.getOriginalFilename().endsWith(".jpg")||file.getOriginalFilename().
	 * endsWith(".jpeg")||file.getOriginalFilename().endsWith(".png"))) throw new
	 * FileStorageException("Only PNG, JPEG and JPG images are allowed"); File f =
	 * new File("E://TMP//"); return null; }
	 * 
	 * @Override public Resource loadFileAsResource(String filename) { // TODO
	 * Auto-generated method stub return null; }
	 */

}
