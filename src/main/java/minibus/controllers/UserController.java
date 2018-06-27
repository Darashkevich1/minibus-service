package minibus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import minibus.UserRole;
import minibus.entities.Token;
import minibus.services.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userServiceImpl;
	
	@RequestMapping("/register")
	public Token test(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("role") String role) {
		return userServiceImpl.registerUser(email, password, UserRole.valueOf(role));
	}
	
	@RequestMapping("/logout")
	public boolean logout(@RequestParam("token") String token) {
		return userServiceImpl.logout(token);
	}
	
	@RequestMapping("/login")
	public Token login(@RequestParam("email") String email, @RequestParam("password") String password) {
		return userServiceImpl.login(email, password);
	}
	
	@RequestMapping("/delete")
	public boolean delete(@RequestParam("email") String email, @RequestParam("accessToken") String token) {
		return userServiceImpl.deleteUser(email, token);
	}
}