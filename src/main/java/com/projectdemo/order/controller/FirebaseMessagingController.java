package com.projectdemo.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.projectdemo.order.bean.Note;
import com.projectdemo.order.service.FirebaseMessagingService;

@RestController
@CrossOrigin()
public class FirebaseMessagingController {

	@Autowired FirebaseMessagingService firebaseService;
	
	
	@PostMapping("/send-notification")
	@ResponseBody
	public String sendNotification(@RequestBody Note note,
	                               @RequestParam String token) throws FirebaseMessagingException {
	    return firebaseService.sendNotification(note, token);
	}
	
}
