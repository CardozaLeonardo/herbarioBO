package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EspecimenController {
	
	@GetMapping("/fichas/newPlant")
	public String nuevaPlanta() {
		return "/fichas/newPlant.jsp";
	}
}
