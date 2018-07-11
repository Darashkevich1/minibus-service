package minibus.dao;

import java.util.List;

import minibus.entities.Car;

public interface CarDAO {
	Car getById(int id);
	List<Car> getByDriverId(int driverId);
	Car getByRegisterNumber(String registerNumber);
	
	void addCar(Car car);
	void removeCar(Car car);
}
