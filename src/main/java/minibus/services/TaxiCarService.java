package minibus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import minibus.UserRole;
import minibus.dao.CarDAO;
import minibus.dao.TokenDAO;
import minibus.dao.UserDAO;
import minibus.entities.Car;
import minibus.entities.TaxiCar;
import minibus.entities.Token;
import minibus.entities.User;

@Service
public final class TaxiCarService implements CarService {

	@Autowired
	UserDAO userDataAccess;
	
	@Autowired
	TokenDAO accessTokenDAO;
	
	@Autowired
	CarDAO taxiCarDAO;
	
	@Override
	public Car addCar(String registerNumber, String info, int driverId, int seatsCount, String accessToken) {
		Token token = accessTokenDAO.getByToken(accessToken);
		User userDriverId = null;
		User userToken = null;
		Car car = taxiCarDAO.getByRegisterNumber(registerNumber);
		if(token != null && car == null) {
			userDriverId = userDataAccess.getById(driverId);
			userToken = userDataAccess.getById(token.getUserId());
			if(userDriverId != null && userDriverId.getRole() == UserRole.Driver && userToken != null && 
					(userDriverId.getId() == userToken.getId() || userToken.getRole() == UserRole.Admin)) {
				Car new_car = new TaxiCar();
				new_car.setDriverId(driverId);
				new_car.setRegisterNumber(registerNumber);
				new_car.setInfo(info);
				new_car.setSeatsCount(seatsCount);
				taxiCarDAO.addCar(new_car);
				return taxiCarDAO.getByRegisterNumber(registerNumber);
			}
		}
		return null;
	}

	@Override
	public boolean deleteCar(int id, String accessToken) {
		Token token = accessTokenDAO.getByToken(accessToken);
		if(token != null) {
			User user = userDataAccess.getById(token.getUserId());
			if(user != null) {
				Car car = taxiCarDAO.getById(id);
				if(car != null && (car.getDriverId() == user.getId() || user.getRole() == UserRole.Admin)) {
					taxiCarDAO.removeCar(car);
					return true;
				}
			}
		}
		return false;
	}

}
