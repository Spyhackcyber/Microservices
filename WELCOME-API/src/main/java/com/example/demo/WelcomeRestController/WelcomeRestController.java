package com.example.demo.WelcomeRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.RandomClient;


@RestController
public class WelcomeRestController {

@Autowired
private RandomClient randomclient;

@GetMapping("/welcome")
public String getWelcomeMsg() {
	String Welcome="Hello How ARe You";
	
	String randomMsg=randomclient.invokeRandomApi();
	
	return randomMsg+",,,,,,,,,,"+Welcome;
}
}
