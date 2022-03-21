package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.data.page;

@ControllerAdvice
public class Common {

	@Autowired
	private RestTemplate rest;

	@ModelAttribute
	public void sharedData(Model model) {

		ResponseEntity<List<page>> response = rest.exchange("http://localhost:8080/pages/all", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<page>>(){});
		
		List<page> pages = response.getBody();
		
		model.addAttribute("cpages", pages);
	}

}
