package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="RANDOM-API")
public interface RandomClient {
	
	@GetMapping("/greet")
	public String invokeRandomApi();
	//Declarative Abstaract Methode

}
