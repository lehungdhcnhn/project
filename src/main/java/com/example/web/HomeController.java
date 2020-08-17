package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.Message;
import com.example.repositories.MessageRepository;


@Controller
public class HomeController
{
	@Autowired
	private MessageRepository messageRepository;
	
	
	
	@PostMapping("/messages")
	public String saveMessage(Message message)
	{
		messageRepository.save(message);
		return "redirect:/home";
	}
}
