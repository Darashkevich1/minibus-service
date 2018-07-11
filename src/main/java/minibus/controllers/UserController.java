package minibus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import minibus.UserRole;
import minibus.entities.Token;
import minibus.entities.User;
import minibus.services.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService userServiceImpl;
	
	@RequestMapping("/register")
	public Token register(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("role") String role) {
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
	public boolean delete(@RequestParam("id") int id, @RequestParam("accessToken") String token) {
		return userServiceImpl.deleteUser(id, token);
	}
	
	@RequestMapping("/{id}")
	public User byId(@PathVariable("id") int id) {
		return userServiceImpl.getById(id);
	}
	
	@RequestMapping("/update")
	public boolean update(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,
			@RequestParam("phone") String phone, @RequestParam("address") String address, @RequestParam("accessToken") String accessToken) {
		return userServiceImpl.updateUser(firstname, lastname, phone, address, accessToken);
	}
	
	@RequestMapping("/emailIsFree")
	public boolean emailIsFree(@RequestParam("email") String email) {
		return userServiceImpl.emailIsFree(email);
	}
}
