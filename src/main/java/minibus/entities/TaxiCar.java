package minibus.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public final class TaxiCar implements Car {
	
	@Id
	@GeneratedValue
	private int id;
	
	private int driverId, seatsCount;
	private String info;
	
	@Column(unique = true)
	private String registerNumber;
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getRegisterNumber() {
		return registerNumber;
	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	@Override
	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public int getDriverId() {
		return driverId;
	}

	@Override
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	@Override
	public int getSeatsCount() {
		return seatsCount;
	}

	@Override
	public void setSeatsCount(int seatsCount) {
		this.seatsCount = seatsCount;
	}

}
