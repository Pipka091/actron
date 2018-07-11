package ru.test.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class SpringController {

	@RequestMapping(name = "/", method = RequestMethod.GET)
	public String homePage() {
		return "index";
	}


}
