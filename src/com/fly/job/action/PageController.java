package com.fly.job.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/chenmeng")
public class PageController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home() {
		return "index";
	}
	
}