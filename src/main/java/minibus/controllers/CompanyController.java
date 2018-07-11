package minibus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import minibus.entities.Company;
import minibus.services.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	CompanyService taxiCompanyService;
	
	@RequestMapping("/add")
	public Company add(@RequestParam("name") String name, @RequestParam("ownerId") int ownerId, @RequestParam("accessToken") String accessToken) {
		return taxiCompanyService.addCompany(name, ownerId, accessToken);
	}
	
	@RequestMapping("/delete")
	public boolean delete(@RequestParam("id") int id, @RequestParam("accessToken") String accessToken) {
		return taxiCompanyService.deleteCompany(id, accessToken);
	}
}
