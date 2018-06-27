package minibus.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import minibus.UserRole;
import minibus.dao.CarDAO;
import minibus.dao.TokenDAO;
import minibus.dao.TripDAO;
import minibus.dao.UserDAO;
import minibus.entities.Car;
import minibus.entities.TaxiTrip;
import minibus.entities.Token;
import minibus.entities.Trip;
import minibus.entities.User;

@Service
public final class TaxiTripService implements TripService {

	@Autowired
	TripDAO taxiTripDAO;
	
	@Autowired
	TokenDAO accessTokenDAO;
	
	@Autowired
	CarDAO taxiCarDAO;
	
	@Autowired
	UserDAO userDataAccess;
	
	@Override
	public Trip addTrip(String departurePoint, Date departureDate, String arrivalPoint, Date arrivalDate, int carId, String accessToken) {
		
		Token token = accessTokenDAO.getByToken(accessToken);
		Car car = taxiCarDAO.getById(carId);
		
		if(car != null && token != null) {
			User user = userDataAccess.getById(token.getUserId());
			if(user != null &&  (token.getUserId() == car.getDriverId() || user.getRole() == UserRole.Admin)) {
				Trip new_trip = new TaxiTrip();
				new_trip.setArrivalDate(arrivalDate);
				new_trip.setArrivalPoint(arrivalPoint);
				new_trip.setDepartureDate(departureDate);
				new_trip.setDeparturePoint(departurePoint);
				new_trip.setCarId(carId);
				taxiTripDAO.addTrip(new_trip);
				List<Trip> trips = taxiTripDAO.getByCarId(carId);
				return trips.get(trips.size()-1);
			}
		}
		
		return null;
	}

	@Override
	public boolean deleteTrip(int id, String accessToken) {
		Token token = accessTokenDAO.getByToken(accessToken);
		Trip t = taxiTripDAO.getById(id);
		User user = null;
		
		if(token != null && t != null) {
			user = userDataAccess.getById(token.getUserId());
			Car car = taxiCarDAO.getById(t.getCarId());
			if(car != null && user != null && (car.getDriverId() == token.getUserId() || user.getRole() == UserRole.Admin)) {
				taxiTripDAO.removeTrip(t);
				return true;
			}
		}
		
		return false;
	}

}
