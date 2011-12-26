package org.codemomentum.spring.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * @author codemomentum@gmail.com
 */
@Controller
@RequestMapping("/")
public class MainPageController {
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		Date now = new Date();
		model.addAttribute("date", now);
		return "main";
	}
}