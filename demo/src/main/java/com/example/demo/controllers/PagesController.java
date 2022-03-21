package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.data.page;

@Controller
@RequestMapping("/")
public class PagesController {

	@Autowired
	private RestTemplate rest;

	@GetMapping("/")
	public String home(Model model) {
		page page = rest.getForObject("http://localhost:8080/pages/home", page.class);
		model.addAttribute("page", page);
		return "page";

	}

	@GetMapping("/{slug}")
	public String page(@PathVariable String slug, Model model) {
		try {
			page page = rest.getForObject("http://localhost:8080/pages/{slug}", page.class, slug);
			model.addAttribute("page", page);
		} catch (final HttpClientErrorException e) {
			return "redirect:/";
		}

		return "page";

	}
}
