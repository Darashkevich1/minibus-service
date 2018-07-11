package minibus.entities;

public interface Car {
	
	int getId();
	int getDriverId();
	String getRegisterNumber();
	String getInfo();
	int getSeatsCount();
	
	void setId(int id);
	void setDriverId(int driverId);
	void setRegisterNumber(String registerNumber);
	void setInfo(String info);
	void setSeatsCount(int seatsCount);
}
