package minibus.entities;

import minibus.UserRole;

public interface User {
	
	int getId();
	String getFirstName();
	String getLastName();
	String getAddress();
	String getEmail();
	String getPassword();
	String getPhone();
	UserRole getRole();
	
	void setId(int id);
	void setFirstName(String firstName);
	void setLastName(String lastName);
	void setAddress(String address);
	void setEmail(String email);
	void setRole(UserRole userRole);
	void setPhone(String phone);
	void setPassword(String password);
}
