package minibus.services;

import java.util.Date;

import minibus.entities.Trip;

public interface TripService {
	Trip addTrip(String departurePoint, Date departureDate, String arrivalPoint, Date arrivalDate, int carId, String accessToken);
	boolean deleteTrip(int id, String accessToken);
}
