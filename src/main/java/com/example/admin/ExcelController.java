package com.example.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.Message;
import com.example.helper.ExelHelper;
import com.example.service.impl.ExelService;

@Controller
public class ExcelController {
	@Autowired
	private ExelService excelService;
	@PostMapping("/uploadCSV")
	  public ResponseEntity<Message> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";

	    if (ExelHelper.hasExcelFormat(file)) {
	      try {
	    	  excelService.save(file);
	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(new Message(message));
	      } catch (Exception e) {
	        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Message(message));
	      }
	    }
	    message = "Please upload an excel file!";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(message));
	  }
}
