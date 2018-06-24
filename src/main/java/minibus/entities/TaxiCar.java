package minibus.entities;

public interface TaxiCar {
	
	int getId();
	User getDriver();
	String getRegisterNumber();
	String getInfo();
	
	void setId(int id);
	void setDriver(User driver);
	void setRegisterNumber(String registerNumber);
	void setInfo(String info);
}
