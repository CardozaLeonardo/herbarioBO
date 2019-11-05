package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.example.dao.DT_user;
//import com.example.entity.User;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="XD") String name, Model model) {
		//DT_user dtu = new DT_user();
		//User[] users = dtu.getUsers();
        //model.addAttribute("name", users[0]);
        //dtu.getUsers();
        //System.out.println("hola");
        return "index.jsp";
    }
}
