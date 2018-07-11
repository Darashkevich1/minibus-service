package minibus.dao;

import java.util.List;

import minibus.entities.Trip;

public interface TripDAO {
	List<Trip> getByDepartureArrival(String departure, String arrival);
	Trip getById(int id);
	List<Trip> getByCarId(int carId);
	
	void addTrip(Trip trip);
	void removeTrip(Trip trip);
}
