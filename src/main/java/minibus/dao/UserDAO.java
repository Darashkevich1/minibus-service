package minibus.dao;

import minibus.entities.User;

public interface UserDAO {
	User getById(int id);
	User getByEmail(String email);
	
	void addUser(User user);
	void removeUser(User user);
}
