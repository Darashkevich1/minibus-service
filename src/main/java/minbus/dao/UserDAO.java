package minbus.dao;

import minibus.entities.User;

public interface UserDAO {
	User getById(int id);
	User getByEmail(String email);
}
