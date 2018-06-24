package minibus.entities;

public interface Company {
	
	int getId();
	int getOwnerId();
	String getName();
	
	void setId(int id);
	void setOwnerId(int ownerId);
	void setName(String name);
	
}
