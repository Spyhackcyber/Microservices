package com.example.demo.ontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomRestController {

	@GetMapping("/greet")
	public String getGreetMsg() {
		String msg="God evening Sir....!!!";
		return msg;
	}
	
}
