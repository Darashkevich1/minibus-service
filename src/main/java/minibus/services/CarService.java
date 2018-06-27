package minibus.services;

import minibus.entities.Car;

public interface CarService {
	Car addCar(String registerNumber, String info, int driverId, int seatsCount, String accessToken);
	boolean deleteCar(int id, String accessToken);
}
