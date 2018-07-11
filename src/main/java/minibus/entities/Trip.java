package minibus.entities;

import java.util.Date;

public interface Trip {
	
	int getId();
	String getDeparturePoint();
	String getArrivalPoint();
	int getCarId();
	Date getDepartureDate();
	Date getArrivalDate();
	
	void setId(int id);
	void setDeparturePoint(String departurePoint);
	void setArrivalPoint(String arrivalPoint);
	void setCarId(int carId);
	void setDepartureDate(Date departureDate);
	void setArrivalDate(Date arrivalDate);
}
