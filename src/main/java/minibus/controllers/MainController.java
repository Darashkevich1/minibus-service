package minibus.controllers;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import minibus.entities.AdminUser;

@RestController
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	
	SessionFactory sessionFactory;
	
	@Transactional
	@RequestMapping("/test")
	public String test() {
		AdminUser user = new AdminUser();
		user.setAddress("sdfsdf");
		user.setEmail("sdfsdfs");
		user.setFirstName("sdfsdf");
		user.setLastName("sdfsdf");
		user.setPhone("sdfsdfsdf");
		Session se = sessionFactory.getCurrentSession();
		se.save(user);
		return "GOOD!";
	}
}
