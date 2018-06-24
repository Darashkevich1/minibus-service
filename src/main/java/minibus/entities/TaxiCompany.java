package minibus.entities;

public interface TaxiCompany {
	
	int getId();
	User getOwner();
	String getName();
	
	void setId(int id);
	void setOwner(User owner);
	void setName(String name);
	
}
