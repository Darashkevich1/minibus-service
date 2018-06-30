package minibus.services;

import minibus.UserRole;
import minibus.entities.Token;
import minibus.entities.User;

public interface UserService {
	Token login(String email, String password);
	boolean logout(String token);
	User getById(int id);
	boolean emailIsFree(String email);
	
	Token registerUser(String email, String password, UserRole role);
	boolean deleteUser(String email, String token);
}
