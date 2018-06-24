package minibus.entities;

import java.util.Date;

public interface Trip {
	
	int getId();
	String getDeparturePoint();
	String getArrivalPoint();
	TaxiCar getTaxiCar();
	Date getDepartureDate();
	Date getArrivalDate();
	
	void setId(int id);
	void setDeparturePoint(String departurePoint);
	void setArrivalPoint(String arrivalPoint);
	void setTaxiCar(TaxiCar taxiCar);
	void setDepartureDate(Date departureDate);
	void setArrivalDate(Date arrivalDate);
}
