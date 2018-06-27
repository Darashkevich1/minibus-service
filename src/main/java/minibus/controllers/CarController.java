package minibus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import minibus.entities.Car;
import minibus.services.CarService;

@RestController
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	CarService taxiCarService;
	
	@RequestMapping("/add")
	public Car add(@RequestParam("registerNumber") String registerNumber, @RequestParam("info") String info, 
			@RequestParam("driverId") int driverId, @RequestParam("seatsCount") int seatsCount, @RequestParam("accessToken") String accessToken) {
		return taxiCarService.addCar(registerNumber, info, driverId, seatsCount, accessToken);
	}
	
	@RequestMapping("/delete")
	public boolean delete(@RequestParam("id") int id, @RequestParam("accessToken") String accessToken) {
		return taxiCarService.deleteCar(id, accessToken);
	}
}
