package minibus.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public final class TaxiTrip implements Trip {

	@Id
	@GeneratedValue
	private int id;
	
	private int carId;
	private String departurePoint, arrivalPoint;
	private Date departureDate, arrivalDate;
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getDeparturePoint() {
		return departurePoint;
	}

	@Override
	public String getArrivalPoint() {
		return arrivalPoint;
	}

	@Override
	public int getCarId() {
		return carId;
	}

	@Override
	public Date getDepartureDate() {
		return departureDate;
	}

	@Override
	public Date getArrivalDate() {
		return arrivalDate;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setDeparturePoint(String departurePoint) {
		this.departurePoint = departurePoint;
	}

	@Override
	public void setArrivalPoint(String arrivalPoint) {
		this.arrivalPoint = arrivalPoint;
	}

	@Override
	public void setCarId(int carId) {
		this.carId = carId;
	}

	@Override
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	@Override
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

}
