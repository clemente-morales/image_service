package mx.com.lania.controllers;

import org.springframework.web.bind.annotation.RestController;

import mx.com.lania.ImageServiceProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
	private ImageServiceProperties properties;

	@Autowired
	public HelloController(ImageServiceProperties properties) {
		this.properties = properties;
	}

	@RequestMapping("/coco/")
	public String index() {
		return properties.getName() + " - Greetings " + properties.getSecurity().getUser() + " from Spring Boot!";
	}

}