package minibus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import minbus.dao.UserDataAccess;



@RestController
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	UserDataAccess userDataAccess;
	
	@RequestMapping("/test")
	public String test() {
		
		
		return "GOOD!";
	}
}
